package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.PoetDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.PoetEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoetAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoetQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoetUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.PoetVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PoetService {

    @Resource
    private PoetDao poetDao;

    public ResponseDTO<PageResult<PoetVO>> query(PoetQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<PoetVO> list = poetDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(PoetAddForm addForm) {
        PoetEntity entity = SmartBeanUtil.copy(addForm, PoetEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        poetDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(PoetUpdateForm updateForm) {
        PoetEntity origin = poetDao.selectById(updateForm.getPoetId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        PoetEntity entity = SmartBeanUtil.copy(updateForm, PoetEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        poetDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long poetId) {
        PoetEntity origin = poetDao.selectById(poetId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        PoetEntity entity = new PoetEntity();
        entity.setPoetId(poetId);
        entity.setDeletedFlag(Boolean.TRUE);
        poetDao.updateById(entity);
        return ResponseDTO.ok();
    }

    public ResponseDTO<String> preview(Long poetId) {
        PoetEntity entity = poetDao.selectById(poetId);
        if (entity == null || Boolean.TRUE.equals(entity.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        String script = entity.getScriptText();
        if (script == null || script.length() == 0) {
            script = entity.getPoetName() + "还没有讲解。";
        }
        return ResponseDTO.ok(script);
    }
}
