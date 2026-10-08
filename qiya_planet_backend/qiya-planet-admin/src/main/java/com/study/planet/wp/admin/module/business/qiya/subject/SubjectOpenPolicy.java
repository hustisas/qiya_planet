package com.study.planet.wp.admin.module.business.qiya.subject;

import com.study.planet.wp.admin.module.business.qiya.domain.entity.SubjectEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 科目能不能开放。内容没齐、年级没定，都不能出现在学生端。
 */
@Component
public class SubjectOpenPolicy {

    public String check(SubjectEntity subject) {
        String error = readyError(subject);
        if (error != null) {
            return error;
        }
        if ("已开放".equals(subject.getStatusName())) {
            return "这个科目已经开放";
        }
        return null;
    }

    public String readyError(SubjectEntity subject) {
        if (subject == null || Boolean.TRUE.equals(subject.getDeletedFlag())) {
            return "科目不存在";
        }
        if (!Boolean.TRUE.equals(subject.getContentReady())) {
            return "还没有内容，不能开放";
        }
        if (StringUtils.isBlank(subject.getGradeScope()) || "未定".equals(subject.getGradeScope().trim())) {
            return "年级范围还没定，不能开放";
        }
        return null;
    }
}
