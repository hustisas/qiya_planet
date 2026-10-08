package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class WorldAddForm {
    @Schema(description = "世界")
    @NotBlank(message = "世界不能为空")
    @Length(max = 32, message = "世界最多32字符")
    private String worldName;

    @Schema(description = "关卡")
    @NotBlank(message = "关卡不能为空")
    @Length(max = 64, message = "关卡最多64字符")
    private String levelName;

    @Schema(description = "题型")
    @Length(max = 64, message = "题型最多64字符")
    private String questionMix;

    @Schema(description = "通过率")
    @Length(max = 16, message = "通过率最多16字符")
    private String passRate;

    @Schema(description = "平均重试")
    @Length(max = 16, message = "平均重试最多16字符")
    private String retryAvg;

    @Schema(description = "掉点词")
    @Length(max = 32, message = "掉点词最多32字符")
    private String weakWord;

    @Schema(description = "说明")
    @Length(max = 255, message = "说明最多255字符")
    private String note;
}
