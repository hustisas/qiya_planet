package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_poet")
public class PoetEntity {

    @TableId(type = IdType.AUTO)
    private Long poetId;

    private String poetName;

    private String dynasty;

    private String intro;

    private String scriptText;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
