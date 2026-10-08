package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PoetVO {
    private Long poetId;

    private String poetName;

    private String dynasty;

    private String intro;

    private String scriptText;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
