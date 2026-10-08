package com.study.planet.wp.admin.module.business.qiya.dao;

import com.study.planet.wp.admin.module.business.qiya.domain.vo.NameCountVO;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.OpsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QiyaReportDao {

    Long countStudent();

    Long countPendingReview();

    Long countReset();

    Long countPayScene();

    Long sumHabit();

    String latestReopenRate();

    OpsVO latestOps();

    List<NameCountVO> masteryList();
}
