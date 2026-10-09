package com.study.planet.wp.admin.module.business.qiya.speech;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 口评配置。密钥只放服务端，App 不保存。
 */
@Data
@Component
@ConfigurationProperties(prefix = "qiya.speech")
public class SpeechProperties {

    /**
     * 厂商标识。目前是 tencent。
     */
    private String provider = "tencent";

    /**
     * 每分钟最多接受多少次评测，避免密钥被刷。
     */
    private int burstPerMinute = 40;

    private Tencent tencent = new Tencent();

    @Data
    public static class Tencent {
        private String secretId = "";
        private String secretKey = "";
        private String region = "";
        private String host = "soe.tencentcloudapi.com";
        private double scoreCoeff = 1.5D;
        private int connectTimeoutMs = 5000;
        private int readTimeoutMs = 8000;
    }
}