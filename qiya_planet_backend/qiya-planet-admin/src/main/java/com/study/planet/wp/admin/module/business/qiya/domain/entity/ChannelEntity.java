package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_channel")
public class ChannelEntity {

    @TableId(type = IdType.AUTO)
    private Long channelId;

    private String materialName;

    private String statusName;

    private String showPlace;

    private Integer familyCount;

    private String reopenRate;

    private Integer habitCount;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
