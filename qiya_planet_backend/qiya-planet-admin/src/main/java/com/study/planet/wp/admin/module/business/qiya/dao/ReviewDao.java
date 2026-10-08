package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.ReviewEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewDao extends BaseMapper<ReviewEntity> {
    List<ReviewVO> query(Page page, @Param("query") ReviewQueryForm query);
}
