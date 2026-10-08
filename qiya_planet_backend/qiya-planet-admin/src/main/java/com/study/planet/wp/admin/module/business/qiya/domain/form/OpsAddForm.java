package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class OpsAddForm {
    @Schema(description = "今日主按钮")
    @NotBlank(message = "今日主按钮不能为空")
    @Length(max = 64, message = "今日主按钮最多64字符")
    private String todayTask;

    @Schema(description = "节气")
    @NotBlank(message = "节气不能为空")
    @Length(max = 16, message = "节气最多16字符")
    private String festivalName;

    @Schema(description = "皮肤")
    @NotBlank(message = "皮肤不能为空")
    @Length(max = 32, message = "皮肤最多32字符")
    private String skinName;

    @Schema(description = "会员开关")
    private Boolean memberOpen;
}
