package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.StudentDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.StudentEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.StudentAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.StudentQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.StudentUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.StudentVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentDao studentDao;

    public ResponseDTO<PageResult<StudentVO>> query(StudentQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<StudentVO> list = studentDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(StudentAddForm addForm) {
        StudentEntity entity = SmartBeanUtil.copy(addForm, StudentEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        studentDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(StudentUpdateForm updateForm) {
        StudentEntity origin = studentDao.selectById(updateForm.getStudentId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        StudentEntity entity = SmartBeanUtil.copy(updateForm, StudentEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        studentDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long studentId) {
        StudentEntity origin = studentDao.selectById(studentId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        StudentEntity entity = new StudentEntity();
        entity.setStudentId(studentId);
        entity.setDeletedFlag(Boolean.TRUE);
        studentDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
