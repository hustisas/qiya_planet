package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class KnowledgeAddForm {
    @Schema(description = "知识点")
    @NotBlank(message = "知识点不能为空")
    @Length(max = 64, message = "知识点最多64字符")
    private String knowledgeName;

    @Schema(description = "年级")
    @Length(max = 32, message = "年级最多32字符")
    private String gradeName;

    @Schema(description = "练法")
    @Length(max = 64, message = "练法最多64字符")
    private String practiceType;

    @Schema(description = "前置")
    @Length(max = 64, message = "前置最多64字符")
    private String prevName;

    @Schema(description = "状态")
    @Length(max = 16, message = "状态最多16字符")
    private String statusName;
}
