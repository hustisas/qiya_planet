package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PoemAddForm {
    @Schema(description = "标题")
    @NotBlank(message = "标题不能为空")
    @Length(max = 64, message = "标题最多64字符")
    private String title;

    @Schema(description = "作者")
    @NotBlank(message = "作者不能为空")
    @Length(max = 32, message = "作者最多32字符")
    private String authorName;

    @Schema(description = "朝代")
    @Length(max = 16, message = "朝代最多16字符")
    private String dynasty;

    @Schema(description = "体裁")
    @Length(max = 16, message = "体裁最多16字符")
    private String kindName;

    @Schema(description = "出处")
    @Length(max = 64, message = "出处最多64字符")
    private String sourceNote;

    @Schema(description = "原文")
    @NotBlank(message = "原文不能为空")
    @Length(max = 500, message = "原文最多500字符")
    private String content;

    @Schema(description = "注释")
    @Length(max = 500, message = "注释最多500字符")
    private String noteText;

    @Schema(description = "译文")
    @Length(max = 500, message = "译文最多500字符")
    private String translation;
}
