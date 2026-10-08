package com.study.planet.wp.admin.module.business.qiya.subject;

import org.springframework.stereotype.Component;

/**
 * 会员未开放。任何入口都不能写成已开通，也不能带价格。
 */
@Component
public class MemberOpenPolicy {

    public boolean allowOpen() {
        return false;
    }

    public String refuse() {
        return "家庭会员暂未开放，不能写入价格或开通状态";
    }

    public String studentMessage() {
        return "暂未开放。学生端不出现价格，也不提供开通按钮。";
    }
}
