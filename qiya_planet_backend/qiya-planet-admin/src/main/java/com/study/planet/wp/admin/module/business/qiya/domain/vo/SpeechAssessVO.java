package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SpeechAssessVO {

    @Schema(description = "scored 已打星，unclear 没听清，unconfigured 未接通，failed 评测失败")
    private String status;

    @Schema(description = "0 到 3。只有 scored 才有意义")
    private Integer stars;

    @Schema(description = "完美、中等、一般、不准确")
    private String label;

    @Schema(description = "给孩子看的话，不带百分制")
    private String hint;

    @Schema(description = "本次是否占用跟读次数")
    private Boolean countQuota;

    public static SpeechAssessVO of(String status, Integer stars, String label, String hint, boolean countQuota) {
        SpeechAssessVO vo = new SpeechAssessVO();
        vo.setStatus(status);
        vo.setStars(stars);
        vo.setLabel(label);
        vo.setHint(hint);
        vo.setCountQuota(Boolean.valueOf(countQuota));
        return vo;
    }
}