package com.study.planet.wp.admin.module.business.qiya.speech;

/**
 * 口语评测厂商。新增厂商时实现这个接口即可，评测入口不用改。
 */
public interface SpeechProvider {

    /**
     * 配置里的厂商标识，例如 tencent。
     */
    String code();

    /**
     * 密钥是否已经配好。没配好时不能打分。
     */
    boolean ready();

    /**
     * 按参考文本评测一段录音。失败抛出异常，不返回猜测分数。
     */
    SpeechAssessResult assess(SpeechAssessCommand command);
}