package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OpsVO {
    private Long opsId;

    private String todayTask;

    private String festivalName;

    private String skinName;

    private Boolean memberOpen;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
