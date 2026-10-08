package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.PoemDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.PoemEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoemAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoemQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoemUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.PoemVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PoemService {

    @Resource
    private PoemDao poemDao;

    public ResponseDTO<PageResult<PoemVO>> query(PoemQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<PoemVO> list = poemDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(PoemAddForm addForm) {
        PoemEntity entity = SmartBeanUtil.copy(addForm, PoemEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        poemDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(PoemUpdateForm updateForm) {
        PoemEntity origin = poemDao.selectById(updateForm.getPoemId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        PoemEntity entity = SmartBeanUtil.copy(updateForm, PoemEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        poemDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long poemId) {
        PoemEntity origin = poemDao.selectById(poemId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        PoemEntity entity = new PoemEntity();
        entity.setPoemId(poemId);
        entity.setDeletedFlag(Boolean.TRUE);
        poemDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
