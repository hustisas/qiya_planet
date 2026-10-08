package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class WordAddForm {
    @Schema(description = "英文")
    @NotBlank(message = "英文不能为空")
    @Length(max = 64, message = "英文最多64字符")
    private String en;

    @Schema(description = "中文")
    @NotBlank(message = "中文不能为空")
    @Length(max = 64, message = "中文最多64字符")
    private String zh;

    @Schema(description = "音标")
    @Length(max = 64, message = "音标最多64字符")
    private String phonetic;

    @Schema(description = "年级")
    @Length(max = 32, message = "年级最多32字符")
    private String gradeName;

    @Schema(description = "主题")
    @Length(max = 32, message = "主题最多32字符")
    private String themeName;

    @Schema(description = "来源")
    @Length(max = 32, message = "来源最多32字符")
    private String sourceName;

    @Schema(description = "掌握")
    @Length(max = 16, message = "掌握最多16字符")
    private String mastery;

    @Schema(description = "例句")
    @Length(max = 255, message = "例句最多255字符")
    private String sentence;
}
