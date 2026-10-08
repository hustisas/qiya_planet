package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewAddForm {
    @Schema(description = "科目编码")
    @NotBlank(message = "科目编码不能为空")
    @Length(max = 16, message = "科目编码最多16字符")
    private String subjectCode;

    @Schema(description = "科目")
    @NotBlank(message = "科目不能为空")
    @Length(max = 16, message = "科目最多16字符")
    private String subjectName;

    @Schema(description = "对象")
    @NotBlank(message = "对象不能为空")
    @Length(max = 64, message = "对象最多64字符")
    private String targetName;

    @Schema(description = "内容")
    @NotBlank(message = "内容不能为空")
    @Length(max = 500, message = "内容最多500字符")
    private String content;

    @Schema(description = "状态")
    @NotBlank(message = "状态不能为空")
    @Length(max = 16, message = "状态最多16字符")
    private String statusName;
}
