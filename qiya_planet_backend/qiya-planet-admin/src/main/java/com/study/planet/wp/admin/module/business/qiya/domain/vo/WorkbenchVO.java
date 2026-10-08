package com.study.planet.wp.admin.module.business.qiya.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class WorkbenchVO {

    private Long studentCount;

    private Long pendingReview;

    private Long resetCount;

    private Long paySceneCount;

    private Long habitCount;

    private String reopenRate;

    private String memberIncome;

    private String todayTask;

    private String festivalName;

    private List<NameCountVO> masteryList;
}
