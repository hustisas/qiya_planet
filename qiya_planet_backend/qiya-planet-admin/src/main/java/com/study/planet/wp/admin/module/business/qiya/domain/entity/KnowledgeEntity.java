package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_knowledge")
public class KnowledgeEntity {

    @TableId(type = IdType.AUTO)
    private Long knowledgeId;

    private String knowledgeName;

    private String gradeName;

    private String practiceType;

    private String prevName;

    private String statusName;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
