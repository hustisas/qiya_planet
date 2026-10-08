package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class StudentAddForm {
    @Schema(description = "孩子")
    @NotBlank(message = "孩子不能为空")
    @Length(max = 32, message = "孩子最多32字符")
    private String childName;

    @Schema(description = "年级")
    @Length(max = 32, message = "年级最多32字符")
    private String gradeName;

    @Schema(description = "家长")
    @Length(max = 32, message = "家长最多32字符")
    private String parentName;

    @Schema(description = "最近")
    @Length(max = 128, message = "最近最多128字符")
    private String recentText;

    @Schema(description = "状态")
    @Length(max = 16, message = "状态最多16字符")
    private String statusName;
}
