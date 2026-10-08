package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.SubjectDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.SubjectEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SubjectAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SubjectQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SubjectUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.SubjectVO;
import com.study.planet.wp.admin.module.business.qiya.subject.SubjectOpenPolicy;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectService {

    private static final String OPEN_ENTRY = "只出现在学生地图，不加第五个主按钮";

    @Resource
    private SubjectDao subjectDao;

    @Resource
    private SubjectOpenPolicy subjectOpenPolicy;

    public ResponseDTO<PageResult<SubjectVO>> query(SubjectQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SubjectVO> list = subjectDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(SubjectAddForm addForm) {
        SubjectEntity entity = SmartBeanUtil.copy(addForm, SubjectEntity.class);
        if (StringUtils.isBlank(entity.getStatusName())) {
            entity.setStatusName("未开放");
        }
        if (entity.getContentReady() == null) {
            entity.setContentReady(Boolean.FALSE);
        }
        if ("已开放".equals(entity.getStatusName())) {
            String error = subjectOpenPolicy.readyError(entity);
            if (error != null) {
                return ResponseDTO.userErrorParam(error);
            }
            entity.setEntryText(OPEN_ENTRY);
        } else if (StringUtils.isBlank(entity.getEntryText())) {
            entity.setEntryText("学生端不出现");
        }
        entity.setDeletedFlag(Boolean.FALSE);
        subjectDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(SubjectUpdateForm updateForm) {
        SubjectEntity origin = subjectDao.selectById(updateForm.getSubjectId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        SubjectEntity entity = SmartBeanUtil.copy(updateForm, SubjectEntity.class);
        if ("已开放".equals(entity.getStatusName())) {
            SubjectEntity merged = new SubjectEntity();
            merged.setDeletedFlag(Boolean.FALSE);
            merged.setContentReady(entity.getContentReady() == null ? origin.getContentReady() : entity.getContentReady());
            merged.setGradeScope(StringUtils.isBlank(entity.getGradeScope()) ? origin.getGradeScope() : entity.getGradeScope());
            String error = subjectOpenPolicy.readyError(merged);
            if (error != null) {
                return ResponseDTO.userErrorParam(error);
            }
            entity.setEntryText(OPEN_ENTRY);
        }
        entity.setDeletedFlag(Boolean.FALSE);
        subjectDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long subjectId) {
        SubjectEntity origin = subjectDao.selectById(subjectId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        SubjectEntity entity = new SubjectEntity();
        entity.setSubjectId(subjectId);
        entity.setDeletedFlag(Boolean.TRUE);
        subjectDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> markReady(Long subjectId) {
        SubjectEntity origin = subjectDao.selectById(subjectId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        SubjectEntity entity = new SubjectEntity();
        entity.setSubjectId(subjectId);
        entity.setContentReady(Boolean.TRUE);
        subjectDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> open(Long subjectId) {
        SubjectEntity origin = subjectDao.selectById(subjectId);
        String error = subjectOpenPolicy.check(origin);
        if (error != null) {
            return ResponseDTO.userErrorParam(error);
        }
        SubjectEntity entity = new SubjectEntity();
        entity.setSubjectId(subjectId);
        entity.setStatusName("已开放");
        entity.setEntryText(OPEN_ENTRY);
        subjectDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
