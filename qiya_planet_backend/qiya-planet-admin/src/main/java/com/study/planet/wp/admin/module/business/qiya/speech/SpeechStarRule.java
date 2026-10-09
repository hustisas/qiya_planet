package com.study.planet.wp.admin.module.business.qiya.speech;

/**
 * 把口评分换成孩子能看的星。不把百分制直接给低龄看。
 * 三星完美，二星中等，一星一般，没有星是不准确。
 */
public final class SpeechStarRule {

    private SpeechStarRule() {
    }

    public static int stars(double score) {
        if (score >= 90D) {
            return 3;
        }
        if (score >= 75D) {
            return 2;
        }
        if (score >= 60D) {
            return 1;
        }
        return 0;
    }

    public static String label(int stars) {
        if (stars >= 3) {
            return "完美";
        }
        if (stars == 2) {
            return "中等";
        }
        if (stars == 1) {
            return "一般";
        }
        return "不准确";
    }
}