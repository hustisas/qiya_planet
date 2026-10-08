package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.KnowledgeDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.KnowledgeEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.KnowledgeAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.KnowledgeQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.KnowledgeUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.KnowledgeVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnowledgeService {

    @Resource
    private KnowledgeDao knowledgeDao;

    public ResponseDTO<PageResult<KnowledgeVO>> query(KnowledgeQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<KnowledgeVO> list = knowledgeDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(KnowledgeAddForm addForm) {
        KnowledgeEntity entity = SmartBeanUtil.copy(addForm, KnowledgeEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        knowledgeDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(KnowledgeUpdateForm updateForm) {
        KnowledgeEntity origin = knowledgeDao.selectById(updateForm.getKnowledgeId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        KnowledgeEntity entity = SmartBeanUtil.copy(updateForm, KnowledgeEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        knowledgeDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long knowledgeId) {
        KnowledgeEntity origin = knowledgeDao.selectById(knowledgeId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        KnowledgeEntity entity = new KnowledgeEntity();
        entity.setKnowledgeId(knowledgeId);
        entity.setDeletedFlag(Boolean.TRUE);
        knowledgeDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
