package com.study.planet.wp.admin.module.business.qiya.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_qiya_poem")
public class PoemEntity {

    @TableId(type = IdType.AUTO)
    private Long poemId;

    private String title;

    private String authorName;

    private String dynasty;

    private String kindName;

    private String sourceNote;

    private String content;

    private String noteText;

    private String translation;

    private Boolean deletedFlag;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
