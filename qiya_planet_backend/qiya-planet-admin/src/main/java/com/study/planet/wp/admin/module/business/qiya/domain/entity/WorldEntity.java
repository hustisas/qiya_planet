package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_world")
public class WorldEntity {

    @TableId(type = IdType.AUTO)
    private Long worldId;

    private String worldName;

    private String levelName;

    private String questionMix;

    private String passRate;

    private String retryAvg;

    private String weakWord;

    private String note;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
