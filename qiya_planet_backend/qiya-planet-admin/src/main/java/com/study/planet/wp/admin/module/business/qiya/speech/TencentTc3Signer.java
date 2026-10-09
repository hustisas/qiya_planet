package com.study.planet.wp.admin.module.business.qiya.speech;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 腾讯云 TC3-HMAC-SHA256 签名。只签 content-type 和 host。
 */
public final class TencentTc3Signer {

    private static final char[] HEX = "0123456789abcdef".toCharArray();
    private static final String ALGORITHM = "TC3-HMAC-SHA256";

    private TencentTc3Signer() {
    }

    public static String authorization(String secretId, String secretKey, String service, String host,
                                       String contentType, String payload, String date, long timestamp) {
        try {
            String canonicalHeaders = "content-type:" + contentType + "\n" + "host:" + host + "\n";
            String signedHeaders = "content-type;host";
            String canonicalRequest = "POST\n/\n\n" + canonicalHeaders + "\n" + signedHeaders + "\n" + sha256Hex(payload);
            String scope = date + "/" + service + "/tc3_request";
            String stringToSign = ALGORITHM + "\n" + timestamp + "\n" + scope + "\n" + sha256Hex(canonicalRequest);
            byte[] secretDate = hmac(("TC3" + secretKey).getBytes(StandardCharsets.UTF_8), date);
            byte[] secretService = hmac(secretDate, service);
            byte[] secretSigning = hmac(secretService, "tc3_request");
            String signature = hex(hmac(secretSigning, stringToSign));
            return ALGORITHM + " Credential=" + secretId + "/" + scope
                    + ", SignedHeaders=" + signedHeaders
                    + ", Signature=" + signature;
        } catch (Exception ex) {
            throw new IllegalStateException("sign");
        }
    }

    private static byte[] hmac(byte[] key, String message) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key, "HmacSHA256"));
        return mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    private static String sha256Hex(String value) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return hex(digest.digest(value.getBytes(StandardCharsets.UTF_8)));
    }

    private static String hex(byte[] data) {
        char[] out = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            int value = data[i] & 0xff;
            out[i * 2] = HEX[value >>> 4];
            out[i * 2 + 1] = HEX[value & 0x0f];
        }
        return new String(out);
    }
}