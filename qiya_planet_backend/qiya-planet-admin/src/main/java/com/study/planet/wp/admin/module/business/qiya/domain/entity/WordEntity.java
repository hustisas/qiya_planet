package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_word")
public class WordEntity {

    @TableId(type = IdType.AUTO)
    private Long wordId;

    private String en;

    private String zh;

    private String phonetic;

    private String gradeName;

    private String themeName;

    private String sourceName;

    private String mastery;

    private String sentence;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
