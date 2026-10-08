package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class SubjectAddForm {
    @Schema(description = "科目")
    @NotBlank(message = "科目不能为空")
    @Length(max = 32, message = "科目最多32字符")
    private String subjectName;

    @Schema(description = "状态")
    @Length(max = 16, message = "状态最多16字符")
    private String statusName;

    @Schema(description = "年级范围")
    @Length(max = 32, message = "年级范围最多32字符")
    private String gradeScope;

    @Schema(description = "学生端入口")
    @Length(max = 64, message = "学生端入口最多64字符")
    private String entryText;

    @Schema(description = "内容已齐")
    private Boolean contentReady;
}
