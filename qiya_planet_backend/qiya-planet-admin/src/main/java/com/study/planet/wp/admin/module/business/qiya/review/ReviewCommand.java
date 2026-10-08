package com.study.planet.wp.admin.module.business.qiya.review;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 一次审核决定。策略只看这份命令，不直接改数据库。
 */
@Data
@AllArgsConstructor
public class ReviewCommand {

    private String subjectCode;

    private String targetName;

    private String content;

    private String decision;
}
