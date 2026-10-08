package com.study.planet.wp.admin.module.business.qiya.review;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 语文注释：通过前至少要有一句能讲给孩子听的话。
 */
@Component
public class ChinesePoemReviewStrategy implements ReviewStrategy {

    @Override
    public String subjectCode() {
        return "cn";
    }

    @Override
    public String validate(ReviewCommand command) {
        String decisionError = EnglishMnemonicReviewStrategy.decisionError(command.getDecision());
        if (decisionError != null) {
            return decisionError;
        }
        if ("通过".equals(command.getDecision()) && (StringUtils.isBlank(command.getContent()) || command.getContent().trim().length() < 4)) {
            return "注释太短，不能通过";
        }
        return null;
    }

    @Override
    public String nextStatus(ReviewCommand command) {
        return command.getDecision();
    }
}
