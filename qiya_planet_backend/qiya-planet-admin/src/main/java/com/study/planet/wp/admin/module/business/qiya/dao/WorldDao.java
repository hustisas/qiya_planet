package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.WorldEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WorldQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WorldVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorldDao extends BaseMapper<WorldEntity> {
    List<WorldVO> query(Page page, @Param("query") WorldQueryForm query);
}
