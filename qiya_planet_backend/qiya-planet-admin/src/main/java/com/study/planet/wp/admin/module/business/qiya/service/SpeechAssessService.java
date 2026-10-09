package com.study.planet.wp.admin.module.business.qiya.service;

import com.study.planet.wp.admin.module.business.qiya.domain.form.SpeechAssessForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.SpeechAssessVO;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechAssessCommand;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechAssessResult;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechProperties;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechProvider;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechProviderFactory;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechRateLimiter;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechStarRule;
import com.study.planet.wp.admin.module.business.qiya.speech.SpeechTextRule;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 跟读评测。只在真正打出星时让客户端扣次数。没接通、没听清、调用失败都不扣。
 */
@Slf4j
@Service
public class SpeechAssessService {

    private static final int MAX_AUDIO_CHARS = 700000;

    @Resource
    private SpeechProviderFactory speechProviderFactory;

    @Resource
    private SpeechProperties speechProperties;

    @Resource
    private SpeechRateLimiter speechRateLimiter;

    public ResponseDTO<SpeechAssessVO> assess(SpeechAssessForm form) {
        String refText = form.getRefText() == null ? "" : form.getRefText().trim();
        if (!SpeechTextRule.allowed(refText)) {
            return ResponseDTO.userErrorParam("要读的内容不正确");
        }
        String audio = normalizeAudio(form.getAudioBase64());
        if (audio.length() < 800) {
            return ResponseDTO.ok(SpeechAssessVO.of("unclear", null, "", "没听清。再读一次，这次不扣次数。", false));
        }
        if (audio.length() > MAX_AUDIO_CHARS) {
            return ResponseDTO.userErrorParam("录音太长了");
        }
        String format = form.getAudioFormat() == null ? "" : form.getAudioFormat().trim().toLowerCase();
        if (!"wav".equals(format) && !"mp3".equals(format)) {
            return ResponseDTO.userErrorParam("只接受 wav 或 mp3");
        }
        String subject = "chinese".equals(form.getSubject()) ? "chinese" : "english";
        String evalKind = normalizeKind(form.getEvalKind(), subject);
        if (!speechRateLimiter.tryAcquire(speechProperties.getBurstPerMinute())) {
            return ResponseDTO.ok(SpeechAssessVO.of("failed", null, "", "口评有点忙。先听标准音，这次不扣次数。", false));
        }
        SpeechProvider provider = speechProviderFactory.current();
        if (provider == null || !provider.ready()) {
            return ResponseDTO.ok(SpeechAssessVO.of("unconfigured", null, "", "口评还没接通。先听标准音，这次不打星，也不扣次数。", false));
        }
        try {
            SpeechAssessCommand command = new SpeechAssessCommand(
                    refText, audio, format, subject, evalKind, form.getGradeKey());
            SpeechAssessResult result = provider.assess(command);
            if (!"scored".equals(result.getStatus())) {
                return ResponseDTO.ok(SpeechAssessVO.of("unclear", null, "", "没听清。再读一次，这次不扣次数。", false));
            }
            SpeechAssessVO vo = toScored(result, form.getGradeKey());
            log.info("speech scored stars={}", vo.getStars());
            return ResponseDTO.ok(vo);
        } catch (RuntimeException ex) {
            log.warn("speech assess failed: {}", ex.getMessage());
            return ResponseDTO.ok(SpeechAssessVO.of("failed", null, "", "这次没评成。先听标准音，不扣次数。", false));
        }
    }

    private SpeechAssessVO toScored(SpeechAssessResult result, String gradeKey) {
        int stars = SpeechStarRule.stars(result.getSuggestedScore());
        String label = SpeechStarRule.label(stars);
        boolean showWeak = "g36".equals(gradeKey) || "mid".equals(gradeKey);
        String weak = result.getWeakSound() == null ? "" : result.getWeakSound().trim();
        String hint = hintOf(stars);
        if (showWeak && !weak.isEmpty() && stars < 3) {
            hint = hint + "注意 " + weak + "。";
        }
        return SpeechAssessVO.of("scored", Integer.valueOf(stars), label, hint, true);
    }

    private String hintOf(int stars) {
        if (stars >= 3) {
            return "读得很像。";
        }
        if (stars == 2) {
            return "大体像了，还可以再稳一点。";
        }
        if (stars == 1) {
            return "开口了。再跟标准音读一次。";
        }
        return "这次还不够像。可以放进复习，下次再练。";
    }

    private String normalizeKind(String evalKind, String subject) {
        if ("poem".equals(evalKind)) {
            return "poem";
        }
        if ("sentence".equals(evalKind)) {
            return "sentence";
        }
        if ("chinese".equals(subject)) {
            return "poem";
        }
        return "word";
    }

    private String normalizeAudio(String audioBase64) {
        String audio = audioBase64 == null ? "" : audioBase64.trim();
        if (audio.startsWith("data:")) {
            int comma = audio.indexOf(',');
            if (comma >= 0) {
                audio = audio.substring(comma + 1);
            }
        }
        return audio.replace("\n", "").replace("\r", "");
    }
}