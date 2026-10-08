package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_reset")
public class ResetEntity {

    @TableId(type = IdType.AUTO)
    private Long resetId;

    private String sceneName;

    private String resetKind;

    private Integer resetCount;

    private String payText;

    private String movedText;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
