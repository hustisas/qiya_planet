package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WorldVO {
    private Long worldId;

    private String worldName;

    private String levelName;

    private String questionMix;

    private String passRate;

    private String retryAvg;

    private String weakWord;

    private String note;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
