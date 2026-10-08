package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ResetVO {
    private Long resetId;

    private String sceneName;

    private String resetKind;

    private Integer resetCount;

    private String payText;

    private String movedText;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
