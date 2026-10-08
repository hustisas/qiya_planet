package com.study.planet.wp.admin.module.business.qiya.service;

import com.study.planet.wp.admin.module.business.qiya.dao.QiyaReportDao;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.MemberSummaryVO;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.NameCountVO;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.OpsVO;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WorkbenchVO;
import com.study.planet.wp.admin.module.business.qiya.subject.MemberOpenPolicy;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作台只汇总已经落库的学习数据，不计算价格。
 */
@Service
public class QiyaReportFacade {

    @Resource
    private QiyaReportDao qiyaReportDao;

    @Resource
    private MemberOpenPolicy memberOpenPolicy;

    public ResponseDTO<WorkbenchVO> summary() {
        WorkbenchVO vo = new WorkbenchVO();
        vo.setStudentCount(zero(qiyaReportDao.countStudent()));
        vo.setPendingReview(zero(qiyaReportDao.countPendingReview()));
        vo.setResetCount(zero(qiyaReportDao.countReset()));
        vo.setPaySceneCount(zero(qiyaReportDao.countPayScene()));
        vo.setHabitCount(zero(qiyaReportDao.sumHabit()));
        vo.setReopenRate(StringUtils.defaultIfBlank(qiyaReportDao.latestReopenRate(), "暂无"));
        vo.setMemberIncome("未开通");
        OpsVO ops = qiyaReportDao.latestOps();
        if (ops == null) {
            vo.setTodayTask("还没有今日任务");
            vo.setFestivalName("还没有节气");
        } else {
            vo.setTodayTask(StringUtils.defaultIfBlank(ops.getTodayTask(), "还没有今日任务"));
            vo.setFestivalName(StringUtils.defaultIfBlank(ops.getFestivalName(), "还没有节气"));
        }
        List<NameCountVO> masteryList = qiyaReportDao.masteryList();
        vo.setMasteryList(masteryList == null ? new ArrayList<NameCountVO>() : masteryList);
        return ResponseDTO.ok(vo);
    }

    public ResponseDTO<MemberSummaryVO> member() {
        MemberSummaryVO vo = new MemberSummaryVO();
        vo.setOpen(Boolean.FALSE);
        vo.setMessage(memberOpenPolicy.studentMessage());
        return ResponseDTO.ok(vo);
    }

    private Long zero(Long value) {
        return value == null ? Long.valueOf(0L) : value;
    }
}
