package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.ChannelEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ChannelQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ChannelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelDao extends BaseMapper<ChannelEntity> {
    List<ChannelVO> query(Page page, @Param("query") ChannelQueryForm query);
}
