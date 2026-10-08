package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.ResetEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ResetQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ResetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResetDao extends BaseMapper<ResetEntity> {
    List<ResetVO> query(Page page, @Param("query") ResetQueryForm query);
}
