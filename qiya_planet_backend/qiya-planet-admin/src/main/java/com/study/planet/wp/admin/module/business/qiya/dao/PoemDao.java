package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.PoemEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoemQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.PoemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PoemDao extends BaseMapper<PoemEntity> {
    List<PoemVO> query(Page page, @Param("query") PoemQueryForm query);
}
