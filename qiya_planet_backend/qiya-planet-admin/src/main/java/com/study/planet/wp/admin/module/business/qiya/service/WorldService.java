package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.WorldDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.WorldEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WorldAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WorldQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WorldUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WorldVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorldService {

    @Resource
    private WorldDao worldDao;

    public ResponseDTO<PageResult<WorldVO>> query(WorldQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<WorldVO> list = worldDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(WorldAddForm addForm) {
        WorldEntity entity = SmartBeanUtil.copy(addForm, WorldEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        worldDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(WorldUpdateForm updateForm) {
        WorldEntity origin = worldDao.selectById(updateForm.getWorldId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        WorldEntity entity = SmartBeanUtil.copy(updateForm, WorldEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        worldDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long worldId) {
        WorldEntity origin = worldDao.selectById(worldId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        WorldEntity entity = new WorldEntity();
        entity.setWorldId(worldId);
        entity.setDeletedFlag(Boolean.TRUE);
        worldDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
