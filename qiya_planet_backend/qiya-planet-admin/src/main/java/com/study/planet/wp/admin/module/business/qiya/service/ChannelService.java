package com.study.planet.wp.admin.module.business.qiya.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.dao.ChannelDao;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.ChannelEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ChannelAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ChannelQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ChannelUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ChannelVO;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import com.study.planet.wp.base.common.util.SmartBeanUtil;
import com.study.planet.wp.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChannelService {

    @Resource
    private ChannelDao channelDao;

    public ResponseDTO<PageResult<ChannelVO>> query(ChannelQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ChannelVO> list = channelDao.query(page, queryForm);
        return ResponseDTO.ok(SmartPageUtil.convert2PageResult(page, list));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ChannelAddForm addForm) {
        ChannelEntity entity = SmartBeanUtil.copy(addForm, ChannelEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        channelDao.insert(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> update(ChannelUpdateForm updateForm) {
        ChannelEntity origin = channelDao.selectById(updateForm.getChannelId());
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ChannelEntity entity = SmartBeanUtil.copy(updateForm, ChannelEntity.class);
        entity.setDeletedFlag(Boolean.FALSE);
        channelDao.updateById(entity);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long channelId) {
        ChannelEntity origin = channelDao.selectById(channelId);
        if (origin == null || Boolean.TRUE.equals(origin.getDeletedFlag())) {
            return ResponseDTO.userErrorParam("记录不存在");
        }
        ChannelEntity entity = new ChannelEntity();
        entity.setChannelId(channelId);
        entity.setDeletedFlag(Boolean.TRUE);
        channelDao.updateById(entity);
        return ResponseDTO.ok();
    }
}
