package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WordVO {
    private Long wordId;

    private String en;

    private String zh;

    private String phonetic;

    private String gradeName;

    private String themeName;

    private String sourceName;

    private String mastery;

    private String sentence;

    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
