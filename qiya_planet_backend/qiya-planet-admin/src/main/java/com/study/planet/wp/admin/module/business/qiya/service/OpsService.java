package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.OpsDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.OpsEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.OpsAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.OpsQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.OpsUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.OpsVO;
import com.study.planet.wp.admin.module.business.qiya.subject.MemberOpenPolicy;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OpsService {

    @Resource
    private OpsDao opsDao;

    @Resource
    private MemberOpenPolicy memberOpenPolicy;

    public ResponseDTO<PageResult<OpsVO>> query(OpsQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<OpsVO> list = opsDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(OpsAddForm addForm) {
        ResponseDTO<String> refused = refuseMember(addForm.getMemberOpen());
        if (refused != null) {
            return refused;
        }
        OpsEntity entity = SmartBeanUtil.copy(addForm, OpsEntity.class);
        entity.setMemberOpen(Boolean.FALSE);
        entity.setDeletedFlag(Boolean.FALSE);
        opsDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(OpsUpdateForm updateForm) {
        OpsEntity origin = opsDao.selectById(updateForm.getOpsId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ResponseDTO<String> refused = refuseMember(updateForm.getMemberOpen());
        if (refused != null) {
            return refused;
        }
        OpsEntity entity = SmartBeanUtil.copy(updateForm, OpsEntity.class);
        entity.setMemberOpen(Boolean.FALSE);
        entity.setDeletedFlag(Boolean.FALSE);
        opsDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long opsId) {
        OpsEntity origin = opsDao.selectById(opsId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        OpsEntity entity = new OpsEntity();
        entity.setOpsId(opsId);
        entity.setDeletedFlag(Boolean.TRUE);
        opsDao.updateById(entity);
        return ResponseDTO.ok();
    }

    private ResponseDTO<String> refuseMember(Boolean memberOpen) {
        if (Boolean.TRUE.equals(memberOpen)) {
            return ResponseDTO.userErrorParam(memberOpenPolicy.refuse());
        }
        return null;
    }
}
