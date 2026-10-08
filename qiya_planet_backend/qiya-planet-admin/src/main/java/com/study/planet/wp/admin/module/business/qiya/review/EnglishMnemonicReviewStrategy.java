package com.study.planet.wp.admin.module.business.qiya.review;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 英语助记：空句和儿童不宜的说法不能通过。
 */
@Component
public class EnglishMnemonicReviewStrategy implements ReviewStrategy {

    private static final List<String> BANNED = Arrays.asList("脏", "硬凑", "色情", "暴力");

    @Override
    public String subjectCode() {
        return "en";
    }

    @Override
    public String validate(ReviewCommand command) {
        String decisionError = decisionError(command.getDecision());
        if (decisionError != null) {
            return decisionError;
        }
        if (!"通过".equals(command.getDecision())) {
            return null;
        }
        if (StringUtils.isBlank(command.getContent())) {
            return "助记不能是空的";
        }
        for (String banned : BANNED) {
            if (command.getContent().contains(banned)) {
                return "这句不适合儿童，不能通过";
            }
        }
        return null;
    }

    @Override
    public String nextStatus(ReviewCommand command) {
        return command.getDecision();
    }

    static String decisionError(String decision) {
        if ("通过".equals(decision) || "拒绝".equals(decision)) {
            return null;
        }
        return "只能通过或拒绝";
    }
}
