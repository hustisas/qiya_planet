package com.study.planet.wp.admin.module.business.qiya.domain.form;

import com.study.planet.wp.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ResetQueryForm extends PageParam {
    @Schema(description = "关键字")
    @Length(max = 50, message = "关键字最多50字符")
    private String searchWord;
}
