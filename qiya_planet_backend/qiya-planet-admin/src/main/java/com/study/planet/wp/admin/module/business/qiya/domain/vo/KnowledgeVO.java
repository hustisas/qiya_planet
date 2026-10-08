package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class KnowledgeVO {
    private Long knowledgeId;

    private String knowledgeName;

    private String gradeName;

    private String practiceType;

    private String prevName;

    private String statusName;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
