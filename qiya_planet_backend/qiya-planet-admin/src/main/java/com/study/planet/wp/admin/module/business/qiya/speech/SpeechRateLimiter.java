package com.study.planet.wp.admin.module.business.qiya.speech;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 简单的每分钟次数限制。超限时不调用第三方，也不扣学生的跟读次数。
 */
@Component
public class SpeechRateLimiter {

    private final Deque<Long> marks = new ArrayDeque<Long>();

    public boolean tryAcquire(int limitPerMinute) {
        int limit = limitPerMinute < 1 ? 1 : limitPerMinute;
        long now = System.currentTimeMillis();
        synchronized (marks) {
            while (!marks.isEmpty() && now - marks.peekFirst().longValue() > 60000L) {
                marks.pollFirst();
            }
            if (marks.size() >= limit) {
                return false;
            }
            marks.addLast(Long.valueOf(now));
            return true;
        }
    }
}