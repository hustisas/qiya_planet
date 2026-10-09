package com.study.planet.wp.admin.module.business.qiya.speech;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯云智聆口语评测，一次性接口 TransmitOralProcessWithInit。
 * 用返回的 SuggestedScore 作为原始分，星级由 SpeechStarRule 换算。
 */
@Component
public class TencentSoeSpeechProvider implements SpeechProvider {

    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final MediaType JSON = MediaType.parse(CONTENT_TYPE);
    private static final String ACTION = "TransmitOralProcessWithInit";
    private static final String VERSION = "2018-07-24";
    private static final String SERVICE = "soe";

    private final SpeechProperties properties;
    private final ObjectMapper objectMapper;
    private final OkHttpClient httpClient;

    public TencentSoeSpeechProvider(SpeechProperties properties) {
        this.properties = properties;
        this.objectMapper = new ObjectMapper();
        SpeechProperties.Tencent tencent = properties.getTencent();
        int connect = tencent.getConnectTimeoutMs() < 1000 ? 5000 : tencent.getConnectTimeoutMs();
        int read = tencent.getReadTimeoutMs() < 1000 ? 8000 : tencent.getReadTimeoutMs();
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(connect, TimeUnit.MILLISECONDS)
                .readTimeout(read, TimeUnit.MILLISECONDS)
                .writeTimeout(read, TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public String code() {
        return "tencent";
    }

    @Override
    public boolean ready() {
        SpeechProperties.Tencent tencent = properties.getTencent();
        return filled(tencent.getSecretId()) && filled(tencent.getSecretKey()) && filled(tencent.getHost());
    }

    @Override
    public SpeechAssessResult assess(SpeechAssessCommand command) {
        SpeechProperties.Tencent tencent = properties.getTencent();
        String payload = buildPayload(command, tencent.getScoreCoeff());
        long timestamp = System.currentTimeMillis() / 1000L;
        String date = Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.UTC).toLocalDate()
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
        String host = tencent.getHost().trim();
        String authorization = TencentTc3Signer.authorization(
                tencent.getSecretId().trim(),
                tencent.getSecretKey().trim(),
                SERVICE,
                host,
                CONTENT_TYPE,
                payload,
                date,
                timestamp);
        Request.Builder builder = new Request.Builder()
                .url("https://" + host)
                .header("Host", host)
                .header("Content-Type", CONTENT_TYPE)
                .header("Authorization", authorization)
                .header("X-TC-Action", ACTION)
                .header("X-TC-Timestamp", String.valueOf(timestamp))
                .header("X-TC-Version", VERSION)
                .post(RequestBody.create(JSON, payload.getBytes(StandardCharsets.UTF_8)));
        if (filled(tencent.getRegion())) {
            builder.header("X-TC-Region", tencent.getRegion().trim());
        }
        try (Response response = httpClient.newCall(builder.build()).execute()) {
            ResponseBody body = response.body();
            String text = body == null ? "" : body.string();
            if (!response.isSuccessful()) {
                throw new IllegalStateException("http");
            }
            return parse(text);
        } catch (IllegalStateException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalStateException("network");
        }
    }

    private String buildPayload(SpeechAssessCommand command, double scoreCoeff) {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("SeqId", 1);
        node.put("IsEnd", 1);
        node.put("VoiceFileType", "wav".equals(command.getAudioFormat()) ? 2 : 3);
        node.put("VoiceEncodeType", 1);
        node.put("UserVoiceData", command.getAudioBase64());
        node.put("SessionId", UUID.randomUUID().toString());
        node.put("RefText", command.getRefText());
        node.put("WorkMode", 1);
        node.put("EvalMode", evalMode(command));
        node.put("ScoreCoeff", normalizeCoeff(scoreCoeff));
        node.put("ServerType", "chinese".equals(command.getSubject()) ? 1 : 0);
        node.put("TextMode", 0);
        try {
            return objectMapper.writeValueAsString(node);
        } catch (Exception ex) {
            throw new IllegalStateException("payload");
        }
    }

    private SpeechAssessResult parse(String text) {
        try {
            JsonNode root = objectMapper.readTree(text);
            JsonNode response = root.get("Response");
            if (response == null || response.isNull()) {
                throw new IllegalStateException("empty");
            }
            JsonNode error = response.get("Error");
            if (error != null && !error.isNull()) {
                throw new IllegalStateException(error.path("Code").asText("Error"));
            }
            double suggested = response.path("SuggestedScore").asDouble(-1D);
            double completion = response.path("PronCompletion").asDouble(-1D);
            if (completion > 1D) {
                completion = completion / 100D;
            }
            if (suggested < 0D) {
                return SpeechAssessResult.unclear();
            }
            if (completion >= 0D && completion < 0.3D && suggested < 40D) {
                return SpeechAssessResult.unclear();
            }
            if (suggested > 100D) {
                suggested = 100D;
            }
            return SpeechAssessResult.scored(suggested, weakestPhone(response));
        } catch (IllegalStateException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalStateException("parse");
        }
    }

    private String weakestPhone(JsonNode response) {
        JsonNode words = response.get("Words");
        if (words == null || !words.isArray()) {
            return "";
        }
        double lowest = 101D;
        String phone = "";
        for (JsonNode word : words) {
            JsonNode phones = word.get("PhoneInfos");
            if (phones == null || !phones.isArray()) {
                continue;
            }
            for (JsonNode one : phones) {
                if (one.path("MatchTag").asInt(0) != 0) {
                    continue;
                }
                double accuracy = one.path("PronAccuracy").asDouble(-1D);
                if (accuracy < 0D) {
                    continue;
                }
                if (accuracy < lowest) {
                    lowest = accuracy;
                    phone = one.path("Phone").asText("");
                }
            }
        }
        if (phone.isEmpty() || lowest >= 70D) {
            return "";
        }
        return phone;
    }

    private int evalMode(SpeechAssessCommand command) {
        if ("word".equals(command.getEvalKind())) {
            return 0;
        }
        return 1;
    }

    private double normalizeCoeff(double scoreCoeff) {
        if (scoreCoeff < 1D) {
            return 1D;
        }
        if (scoreCoeff > 4D) {
            return 4D;
        }
        return scoreCoeff;
    }

    private boolean filled(String value) {
        return value != null && !value.trim().isEmpty();
    }
}