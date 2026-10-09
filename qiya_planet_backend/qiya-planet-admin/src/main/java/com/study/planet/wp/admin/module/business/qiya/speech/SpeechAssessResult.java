package com.study.planet.wp.admin.module.business.qiya.speech;

/**
 * 厂商原始结果。星级不在厂商类里换算。
 */
public class SpeechAssessResult {

    private final String status;
    private final double suggestedScore;
    private final String weakSound;

    private SpeechAssessResult(String status, double suggestedScore, String weakSound) {
        this.status = status;
        this.suggestedScore = suggestedScore;
        this.weakSound = weakSound == null ? "" : weakSound;
    }

    public static SpeechAssessResult scored(double suggestedScore, String weakSound) {
        return new SpeechAssessResult("scored", suggestedScore, weakSound);
    }

    public static SpeechAssessResult unclear() {
        return new SpeechAssessResult("unclear", -1D, "");
    }

    public String getStatus() {
        return status;
    }

    public double getSuggestedScore() {
        return suggestedScore;
    }

    public String getWeakSound() {
        return weakSound;
    }
}