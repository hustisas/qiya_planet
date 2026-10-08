package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.AlbumDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.AlbumEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.AlbumAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.AlbumQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.AlbumUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.AlbumVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlbumService {

    @Resource
    private AlbumDao albumDao;

    public ResponseDTO<PageResult<AlbumVO>> query(AlbumQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<AlbumVO> list = albumDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(AlbumAddForm addForm) {
        AlbumEntity entity = SmartBeanUtil.copy(addForm, AlbumEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        albumDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(AlbumUpdateForm updateForm) {
        AlbumEntity origin = albumDao.selectById(updateForm.getAlbumId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        AlbumEntity entity = SmartBeanUtil.copy(updateForm, AlbumEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        albumDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long albumId) {
        AlbumEntity origin = albumDao.selectById(albumId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        AlbumEntity entity = new AlbumEntity();
        entity.setAlbumId(albumId);
        entity.setDeletedFlag(Boolean.TRUE);
        albumDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
