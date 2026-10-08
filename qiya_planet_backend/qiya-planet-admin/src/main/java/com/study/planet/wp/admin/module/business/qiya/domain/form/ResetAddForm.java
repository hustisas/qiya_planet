package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ResetAddForm {
    @Schema(description = "场景")
    @NotBlank(message = "场景不能为空")
    @Length(max = 64, message = "场景最多64字符")
    private String sceneName;

    @Schema(description = "类型")
    @Length(max = 32, message = "类型最多32字符")
    private String resetKind;

    @Schema(description = "次数")
    private Integer resetCount;

    @Schema(description = "会不会收费")
    @Length(max = 64, message = "会不会收费最多64字符")
    private String payText;

    @Schema(description = "接着做了什么")
    @Length(max = 128, message = "接着做了什么最多128字符")
    private String movedText;
}
