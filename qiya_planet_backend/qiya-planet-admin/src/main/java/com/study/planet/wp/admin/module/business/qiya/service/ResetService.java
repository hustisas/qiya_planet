package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.ResetDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.ResetEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ResetAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ResetQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ResetUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ResetVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResetService {

    @Resource
    private ResetDao resetDao;

    public ResponseDTO<PageResult<ResetVO>> query(ResetQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ResetVO> list = resetDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ResetAddForm addForm) {
        ResetEntity entity = SmartBeanUtil.copy(addForm, ResetEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        resetDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ResetUpdateForm updateForm) {
        ResetEntity origin = resetDao.selectById(updateForm.getResetId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ResetEntity entity = SmartBeanUtil.copy(updateForm, ResetEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        resetDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long resetId) {
        ResetEntity origin = resetDao.selectById(resetId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ResetEntity entity = new ResetEntity();
        entity.setResetId(resetId);
        entity.setDeletedFlag(Boolean.TRUE);
        resetDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
