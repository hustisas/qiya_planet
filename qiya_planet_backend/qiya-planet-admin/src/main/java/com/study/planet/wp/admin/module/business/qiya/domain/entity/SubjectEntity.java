package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_subject")
public class SubjectEntity {

    @TableId(type = IdType.AUTO)
    private Long subjectId;

    private String subjectName;

    private String statusName;

    private String gradeScope;

    private String entryText;

    private Boolean contentReady;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
