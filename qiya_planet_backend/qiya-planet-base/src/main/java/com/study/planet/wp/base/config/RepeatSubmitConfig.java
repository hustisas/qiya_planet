package com.study.planet.wp.base.config;

import com.study.planet.wp.base.common.constant.StringConst;
import com.study.planet.wp.base.common.util.SmartRequestUtil;
import com.study.planet.wp.base.module.support.repeatsubmit.RepeatSubmitAspect;
import com.study.planet.wp.base.module.support.repeatsubmit.ticket.RepeatSubmitRedisTicket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 重复提交配置
 *
 * @Author 1024创新实验室: 罗伊
 * @Date 2021/10/9 18:47
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */
@Configuration
public class RepeatSubmitConfig {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public RepeatSubmitAspect repeatSubmitAspect() {
        RepeatSubmitRedisTicket ticket = new RepeatSubmitRedisTicket(redisTemplate, this::ticket);
        return new RepeatSubmitAspect(ticket);
    }

    /**
     * 获取指明某个用户的凭证
     */
    private String ticket(HttpServletRequest request) {
        Long userId = SmartRequestUtil.getRequestUserId();
        if (null == userId) {
            return StringConst.EMPTY;
        }
        return request.getServletPath() + "_" + userId;
    }
}
