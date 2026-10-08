package com.study.planet.wp.admin.module.business.qiya.review;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 按科目挑选审核策略。新增科目时只加一个策略类，不用改审核入口。
 */
@Component
public class ReviewStrategyFactory {

    private final Map<String, ReviewStrategy> strategyMap = new HashMap<String, ReviewStrategy>();

    private final ReviewStrategy defaultStrategy;

    public ReviewStrategyFactory(List<ReviewStrategy> strategies) {
        ReviewStrategy fallback = null;
        for (ReviewStrategy strategy : strategies) {
            if ("*".equals(strategy.subjectCode())) {
                fallback = strategy;
            } else {
                strategyMap.put(strategy.subjectCode(), strategy);
            }
        }
        this.defaultStrategy = fallback;
    }

    public ReviewStrategy get(String subjectCode) {
        ReviewStrategy strategy = strategyMap.get(subjectCode);
        return strategy == null ? defaultStrategy : strategy;
    }
}
