package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.ReviewDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.ReviewEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewDecideForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ReviewVO;
import com.study.planet.wp.admin.module.business.qiya.review.ReviewCommand;
import com.study.planet.wp.admin.module.business.qiya.review.ReviewStrategy;
import com.study.planet.wp.admin.module.business.qiya.review.ReviewStrategyFactory;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewService {

    @Resource
    private ReviewDao reviewDao;

    @Resource
    private ReviewStrategyFactory reviewStrategyFactory;

    public ResponseDTO<PageResult<ReviewVO>> query(ReviewQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ReviewVO> list = reviewDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ReviewAddForm addForm) {
        ReviewEntity entity = SmartBeanUtil.copy(addForm, ReviewEntity.class);
        entity.setStatusName("草稿");
        entity.setDeletedFlag(Boolean.FALSE);
        reviewDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ReviewUpdateForm updateForm) {
        ReviewEntity origin = reviewDao.selectById(updateForm.getReviewId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ReviewEntity entity = SmartBeanUtil.copy(updateForm, ReviewEntity.class);
        entity.setStatusName(origin.getStatusName());
        entity.setDeletedFlag(Boolean.FALSE);
        reviewDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long reviewId) {
        ReviewEntity origin = reviewDao.selectById(reviewId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ReviewEntity entity = new ReviewEntity();
        entity.setReviewId(reviewId);
        entity.setDeletedFlag(Boolean.TRUE);
        reviewDao.updateById(entity);
        return ResponseDTO.ok();
    }

    /**
     * 通过或拒绝。具体能不能过，交给对应科目的策略。
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> decide(ReviewDecideForm form) {
        ReviewEntity origin = reviewDao.selectById(form.getReviewId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ReviewStrategy strategy = reviewStrategyFactory.get(origin.getSubjectCode());
        if (strategy == null) {
            return ResponseDTO.userErrorParam("没有可用的审核规则");
        }
        ReviewCommand command = new ReviewCommand(origin.getSubjectCode(), origin.getTargetName(), origin.getContent(), form.getDecision());
        String error = strategy.validate(command);
        if (error != null) {
            return ResponseDTO.userErrorParam(error);
        }
        ReviewEntity entity = new ReviewEntity();
        entity.setReviewId(origin.getReviewId());
        entity.setStatusName(strategy.nextStatus(command));
        reviewDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
