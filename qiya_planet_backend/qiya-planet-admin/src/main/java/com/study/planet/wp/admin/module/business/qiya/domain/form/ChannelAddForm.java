package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ChannelAddForm {
    @Schema(description = "素材")
    @NotBlank(message = "素材不能为空")
    @Length(max = 64, message = "素材最多64字符")
    private String materialName;

    @Schema(description = "状态")
    @Length(max = 16, message = "状态最多16字符")
    private String statusName;

    @Schema(description = "出现位置")
    @Length(max = 64, message = "出现位置最多64字符")
    private String showPlace;

    @Schema(description = "邀请家庭")
    private Integer familyCount;

    @Schema(description = "7日回打开")
    @Length(max = 16, message = "7日回打开最多16字符")
    private String reopenRate;

    @Schema(description = "连续满4天")
    private Integer habitCount;
}
