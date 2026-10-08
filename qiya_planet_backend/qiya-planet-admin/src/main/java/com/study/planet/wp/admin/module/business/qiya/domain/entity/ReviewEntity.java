package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_review")
public class ReviewEntity {

    @TableId(type = IdType.AUTO)
    private Long reviewId;

    private String subjectCode;

    private String subjectName;

    private String targetName;

    private String content;

    private String statusName;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
