package com.study.planet.wp.admin.module.business.qiya.review;

/**
 * 不同科目的审核规则。通过或拒绝前先校验，再给出要写入的状态。
 */
public interface ReviewStrategy {

    /**
     * 科目编码。星号表示兜底策略。
     */
    String subjectCode();

    /**
     * 返回 null 表示可以执行。
     */
    String validate(ReviewCommand command);

    String nextStatus(ReviewCommand command);
}
