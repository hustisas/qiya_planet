package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PoetAddForm {
    @Schema(description = "诗人")
    @NotBlank(message = "诗人不能为空")
    @Length(max = 32, message = "诗人最多32字符")
    private String poetName;

    @Schema(description = "朝代")
    @Length(max = 16, message = "朝代最多16字符")
    private String dynasty;

    @Schema(description = "简介")
    @Length(max = 500, message = "简介最多500字符")
    private String intro;

    @Schema(description = "讲解")
    @Length(max = 500, message = "讲解最多500字符")
    private String scriptText;
}
