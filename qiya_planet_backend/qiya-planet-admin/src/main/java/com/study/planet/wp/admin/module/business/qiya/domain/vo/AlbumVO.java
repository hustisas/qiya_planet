package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AlbumVO {
    private Long albumId;

    private String albumName;

    private String levelName;

    private String minutes;

    private Boolean enabledFlag;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
