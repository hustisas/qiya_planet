package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentVO {
    private Long studentId;

    private String childName;

    private String gradeName;

    private String parentName;

    private String recentText;

    private String statusName;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
