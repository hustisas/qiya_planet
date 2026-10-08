package com.study.planet.wp.admin.module.business.qiya.review;

import org.springframework.stereotype.Component;

/**
 * 还没有单独规则的科目，只检查决定本身。
 */
@Component
public class DefaultReviewStrategy implements ReviewStrategy {

    @Override
    public String subjectCode() {
        return "*";
    }

    @Override
    public String validate(ReviewCommand command) {
        return EnglishMnemonicReviewStrategy.decisionError(command.getDecision());
    }

    @Override
    public String nextStatus(ReviewCommand command) {
        return command.getDecision();
    }
}
