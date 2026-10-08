package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SubjectVO {
    private Long subjectId;

    private String subjectName;

    private String statusName;

    private String gradeScope;

    private String entryText;

    private Boolean contentReady;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
