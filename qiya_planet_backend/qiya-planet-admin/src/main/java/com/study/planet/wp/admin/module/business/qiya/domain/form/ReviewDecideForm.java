package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewDecideForm {

    @Schema(description = "审核主键")
    @NotNull(message = "审核主键不能为空")
    private Long reviewId;

    @Schema(description = "通过或拒绝")
    @NotBlank(message = "审核决定不能为空")
    private String decision;
}
