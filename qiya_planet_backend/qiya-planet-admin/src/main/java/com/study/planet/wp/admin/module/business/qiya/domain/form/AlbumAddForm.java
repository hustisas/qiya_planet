package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AlbumAddForm {
    @Schema(description = "专辑")
    @NotBlank(message = "专辑不能为空")
    @Length(max = 64, message = "专辑最多64字符")
    private String albumName;

    @Schema(description = "适龄")
    @Length(max = 32, message = "适龄最多32字符")
    private String levelName;

    @Schema(description = "时长")
    @Length(max = 16, message = "时长最多16字符")
    private String minutes;

    @Schema(description = "启用")
    private Boolean enabledFlag;
}
