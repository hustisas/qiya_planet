package com.study.planet.wp.admin.module.business.qiya.review;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 数学草稿：要通过，必须先写出原因，不能只给答案。
 */
@Component
public class MathDraftReviewStrategy implements ReviewStrategy {

    @Override
    public String subjectCode() {
        return "math";
    }

    @Override
    public String validate(ReviewCommand command) {
        String decisionError = EnglishMnemonicReviewStrategy.decisionError(command.getDecision());
        if (decisionError != null) {
            return decisionError;
        }
        if (!"通过".equals(command.getDecision())) {
            return null;
        }
        if (StringUtils.isBlank(command.getContent())) {
            return "题目说明不能是空的";
        }
        if (!command.getContent().contains("因为") && !command.getContent().contains("原因")) {
            return "数学题要通过，必须先写原因";
        }
        return null;
    }

    @Override
    public String nextStatus(ReviewCommand command) {
        return command.getDecision();
    }
}
