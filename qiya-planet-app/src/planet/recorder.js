const TARGET_RATE = 16000;
const MAX_SECONDS = 8;

let active = null;

export function beginRecording() {
  discardRecording();
  const session = createSession();
  active = session;
  return session.start().then(() => {
    if (active !== session) {
      session.cancel();
      return false;
    }
    return true;
  });
}

export function finishRecording() {
  const session = active;
  active = null;
  if (!session) {
    return Promise.resolve({ ok: false, reason: "empty" });
  }
  return session.stop();
}

export function discardRecording() {
  const session = active;
  active = null;
  if (session) session.cancel();
}

function createSession() {
  if (canUseWebAudio()) return createWebSession();
  return createNativeSession();
}

function canUseWebAudio() {
  if (typeof window === "undefined" || typeof navigator === "undefined") return false;
  if (!navigator.mediaDevices || !navigator.mediaDevices.getUserMedia) return false;
  if (!window.AudioContext && !window.webkitAudioContext) return false;
  return true;
}

function createWebSession() {
  let stream = null;
  let context = null;
  let source = null;
  let processor = null;
  let gain = null;
  let chunks = [];
  let inputRate = TARGET_RATE;
  let startedAt = 0;
  let stopped = false;

  return {
    start: function () {
      return navigator.mediaDevices.getUserMedia({
        audio: { channelCount: 1, echoCancellation: true, noiseSuppression: true },
      }).then((media) => {
        stream = media;
        const Ctx = window.AudioContext || window.webkitAudioContext;
        context = new Ctx();
        inputRate = context.sampleRate || TARGET_RATE;
        source = context.createMediaStreamSource(stream);
        processor = context.createScriptProcessor(4096, 1, 1);
        gain = context.createGain();
        gain.gain.value = 0;
        processor.onaudioprocess = function (event) {
          if (stopped) return;
          const input = event.inputBuffer.getChannelData(0);
          chunks.push(new Float32Array(input));
        };
        source.connect(processor);
        processor.connect(gain);
        gain.connect(context.destination);
        startedAt = Date.now();
        if (context.state === "suspended" && context.resume) return context.resume();
        return true;
      });
    },
    stop: function () {
      stopped = true;
      const seconds = startedAt ? (Date.now() - startedAt) / 1000 : 0;
      const merged = mergeFloat(chunks);
      const samples = downsample(merged, inputRate, TARGET_RATE);
      cleanupWeb(stream, source, processor, gain, context);
      if (seconds < 0.6 || samples.length < TARGET_RATE * 0.4) {
        return Promise.resolve({ ok: false, reason: "short", seconds: seconds });
      }
      const wav = encodeWav(samples, TARGET_RATE);
      return Promise.resolve({
        ok: true,
        format: "wav",
        audioBase64: toBase64(wav),
        seconds: seconds,
      });
    },
    cancel: function () {
      stopped = true;
      cleanupWeb(stream, source, processor, gain, context);
    },
  };
}

function createNativeSession() {
  let recorder = null;
  let settled = false;
  let stopResolve = null;
  let startedAt = 0;

  return {
    start: function () {
      return new Promise((resolve, reject) => {
        if (!uni.getRecorderManager) {
          reject(new Error("no-recorder"));
          return;
        }
        recorder = uni.getRecorderManager();
        recorder.onStop((res) => {
          const seconds = res && res.duration ? res.duration / 1000 : (Date.now() - startedAt) / 1000;
          if (settled) return;
          settled = true;
          if (seconds < 0.6) {
            if (stopResolve) stopResolve({ ok: false, reason: "short", seconds: seconds });
            return;
          }
          readBase64(res.tempFilePath).then((audio) => {
            if (stopResolve) {
              stopResolve({
                ok: true,
                format: formatOf(res.tempFilePath),
                audioBase64: audio,
                seconds: seconds,
              });
            }
          }).catch(() => {
            if (stopResolve) stopResolve({ ok: false, reason: "read" });
          });
        });
        recorder.onError(() => {
          if (stopResolve) {
            const resolveStop = stopResolve;
            stopResolve = null;
            settled = true;
            resolveStop({ ok: false, reason: "record" });
            return;
          }
          if (!settled) {
            settled = true;
            reject(new Error("record"));
          }
        });
        startedAt = Date.now();
        recorder.start({
          duration: MAX_SECONDS * 1000,
          sampleRate: TARGET_RATE,
          numberOfChannels: 1,
          encodeBitRate: 48000,
          format: "mp3",
        });
        resolve(true);
      });
    },
    stop: function () {
      return new Promise((resolve) => {
        stopResolve = resolve;
        if (!recorder) {
          resolve({ ok: false, reason: "empty" });
          return;
        }
        try {
          recorder.stop();
        } catch (err) {
          resolve({ ok: false, reason: "stop" });
        }
      });
    },
    cancel: function () {
      settled = true;
      if (!recorder) return;
      try {
        recorder.stop();
      } catch (err) {
        settled = true;
      }
    },
  };
}

function readBase64(path) {
  return new Promise((resolve, reject) => {
    if (!path || !uni.getFileSystemManager) {
      reject(new Error("no-file"));
      return;
    }
    uni.getFileSystemManager().readFile({
      filePath: path,
      encoding: "base64",
      success: (res) => resolve(res.data || ""),
      fail: () => reject(new Error("read")),
    });
  });
}

function formatOf(path) {
  const lower = String(path || "").toLowerCase();
  if (lower.indexOf(".wav") >= 0) return "wav";
  return "mp3";
}

function cleanupWeb(stream, source, processor, gain, context) {
  try {
    if (processor) processor.onaudioprocess = null;
    if (source) source.disconnect();
    if (processor) processor.disconnect();
    if (gain) gain.disconnect();
  } catch (err) {
    /* 关闭录音时断开节点失败也不影响结果 */
  }
  if (stream && stream.getTracks) {
    const tracks = stream.getTracks();
    for (let i = 0; i < tracks.length; i += 1) tracks[i].stop();
  }
  if (context && context.close) {
    context.close().catch(() => {});
  }
}

function mergeFloat(chunks) {
  let length = 0;
  for (let i = 0; i < chunks.length; i += 1) length += chunks[i].length;
  const merged = new Float32Array(length);
  let offset = 0;
  for (let i = 0; i < chunks.length; i += 1) {
    merged.set(chunks[i], offset);
    offset += chunks[i].length;
  }
  return merged;
}

function downsample(buffer, inputRate, outputRate) {
  if (!buffer.length) return buffer;
  if (outputRate >= inputRate) return buffer;
  const ratio = inputRate / outputRate;
  const length = Math.round(buffer.length / ratio);
  const result = new Float32Array(length);
  let offset = 0;
  for (let i = 0; i < length; i += 1) {
    const next = Math.round((i + 1) * ratio);
    let sum = 0;
    let count = 0;
    while (offset < next && offset < buffer.length) {
      sum += buffer[offset];
      count += 1;
      offset += 1;
    }
    result[i] = count ? sum / count : 0;
  }
  return result;
}

function encodeWav(samples, sampleRate) {
  const buffer = new ArrayBuffer(44 + samples.length * 2);
  const view = new DataView(buffer);
  writeString(view, 0, "RIFF");
  view.setUint32(4, 36 + samples.length * 2, true);
  writeString(view, 8, "WAVE");
  writeString(view, 12, "fmt ");
  view.setUint32(16, 16, true);
  view.setUint16(20, 1, true);
  view.setUint16(22, 1, true);
  view.setUint32(24, sampleRate, true);
  view.setUint32(28, sampleRate * 2, true);
  view.setUint16(32, 2, true);
  view.setUint16(34, 16, true);
  writeString(view, 36, "data");
  view.setUint32(40, samples.length * 2, true);
  let offset = 44;
  for (let i = 0; i < samples.length; i += 1) {
    let sample = samples[i];
    if (sample > 1) sample = 1;
    if (sample < -1) sample = -1;
    view.setInt16(offset, sample < 0 ? sample * 0x8000 : sample * 0x7fff, true);
    offset += 2;
  }
  return buffer;
}

function writeString(view, offset, text) {
  for (let i = 0; i < text.length; i += 1) view.setUint8(offset + i, text.charCodeAt(i));
}

function toBase64(buffer) {
  if (typeof uni !== "undefined" && uni.arrayBufferToBase64) return uni.arrayBufferToBase64(buffer);
  const bytes = new Uint8Array(buffer);
  const table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  let out = "";
  for (let i = 0; i < bytes.length; i += 3) {
    const a = bytes[i];
    const b = i + 1 < bytes.length ? bytes[i + 1] : 0;
    const c = i + 2 < bytes.length ? bytes[i + 2] : 0;
    const triple = (a << 16) | (b << 8) | c;
    out += table[(triple >> 18) & 63];
    out += table[(triple >> 12) & 63];
    out += i + 1 < bytes.length ? table[(triple >> 6) & 63] : "=";
    out += i + 2 < bytes.length ? table[triple & 63] : "=";
  }
  return out;
}