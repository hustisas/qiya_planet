package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.WordDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.WordEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WordAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WordQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WordUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WordVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WordService {

    @Resource
    private WordDao wordDao;

    public ResponseDTO<PageResult<WordVO>> query(WordQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<WordVO> list = wordDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(WordAddForm addForm) {
        WordEntity entity = SmartBeanUtil.copy(addForm, WordEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        wordDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(WordUpdateForm updateForm) {
        WordEntity origin = wordDao.selectById(updateForm.getWordId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        WordEntity entity = SmartBeanUtil.copy(updateForm, WordEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        wordDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long wordId) {
        WordEntity origin = wordDao.selectById(wordId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        WordEntity entity = new WordEntity();
        entity.setWordId(wordId);
        entity.setDeletedFlag(Boolean.TRUE);
        wordDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
