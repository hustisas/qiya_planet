package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.PoetEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoetQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.PoetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PoetDao extends BaseMapper<PoetEntity> {
    List<PoetVO> query(Page page, @Param("query") PoetQueryForm query);
}
