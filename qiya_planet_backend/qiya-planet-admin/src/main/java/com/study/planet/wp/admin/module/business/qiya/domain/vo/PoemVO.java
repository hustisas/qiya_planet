package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PoemVO {
    private Long poemId;

    private String title;

    private String authorName;

    private String dynasty;

    private String kindName;

    private String sourceNote;

    private String content;

    private String noteText;

    private String translation;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
