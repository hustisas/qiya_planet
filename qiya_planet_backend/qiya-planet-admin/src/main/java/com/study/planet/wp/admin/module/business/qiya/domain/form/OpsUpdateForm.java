package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OpsUpdateForm extends OpsAddForm {
    @Schema(description = "主键")
    @NotNull(message = "主键不能为空")
    private Long opsId;
}
