package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewVO {
    private Long reviewId;

    private String subjectCode;

    private String subjectName;

    private String targetName;

    private String content;

    private String statusName;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
