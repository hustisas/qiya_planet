package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChannelVO {
    private Long channelId;

    private String materialName;

    private String statusName;

    private String showPlace;

    private Integer familyCount;

    private String reopenRate;

    private Integer habitCount;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
