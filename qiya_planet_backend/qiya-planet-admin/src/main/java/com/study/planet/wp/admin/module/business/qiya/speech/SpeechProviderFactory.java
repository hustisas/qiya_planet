package com.study.planet.wp.admin.module.business.qiya.speech;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 按配置挑选口评厂商。新增厂商只加一个实现，不用改评测入口。
 */
@Component
public class SpeechProviderFactory {

    private final Map<String, SpeechProvider> providers = new HashMap<String, SpeechProvider>();
    private final SpeechProperties properties;

    public SpeechProviderFactory(List<SpeechProvider> providerList, SpeechProperties properties) {
        this.properties = properties;
        for (SpeechProvider provider : providerList) {
            providers.put(provider.code(), provider);
        }
    }

    public SpeechProvider current() {
        String code = properties.getProvider();
        if (code == null || code.trim().isEmpty()) {
            code = "tencent";
        }
        return providers.get(code.trim());
    }
}