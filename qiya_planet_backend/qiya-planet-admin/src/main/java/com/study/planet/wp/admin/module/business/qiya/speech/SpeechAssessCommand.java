package com.study.planet.wp.admin.module.business.qiya.speech;

/**
 * 一次跟读评测需要的材料。参考文本必须来自词库或课文，不在这里改写。
 */
public class SpeechAssessCommand {

    private final String refText;
    private final String audioBase64;
    private final String audioFormat;
    private final String subject;
    private final String evalKind;
    private final String gradeKey;

    public SpeechAssessCommand(String refText, String audioBase64, String audioFormat,
                               String subject, String evalKind, String gradeKey) {
        this.refText = refText;
        this.audioBase64 = audioBase64;
        this.audioFormat = audioFormat;
        this.subject = subject;
        this.evalKind = evalKind;
        this.gradeKey = gradeKey;
    }

    public String getRefText() {
        return refText;
    }

    public String getAudioBase64() {
        return audioBase64;
    }

    public String getAudioFormat() {
        return audioFormat;
    }

    public String getSubject() {
        return subject;
    }

    public String getEvalKind() {
        return evalKind;
    }

    public String getGradeKey() {
        return gradeKey;
    }
}