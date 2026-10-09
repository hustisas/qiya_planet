package com.study.planet.wp.admin.module.business.qiya.speech;

import java.util.regex.Pattern;

/**
 * 只接受单词、例句和诗句。避免把口评接口当成任意文本通道。
 */
public final class SpeechTextRule {

    private static final Pattern ALLOWED = Pattern.compile("^[\\u4e00-\\u9fffA-Za-z0-9 ,.'!?，。！？、：；·]{1,80}$");

    private SpeechTextRule() {
    }

    public static boolean allowed(String text) {
        if (text == null) {
            return false;
        }
        String trimmed = text.trim();
        if (trimmed.isEmpty() || trimmed.length() > 80) {
            return false;
        }
        return ALLOWED.matcher(trimmed).matches();
    }
}