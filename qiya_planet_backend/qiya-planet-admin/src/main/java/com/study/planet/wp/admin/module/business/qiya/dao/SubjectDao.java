package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.SubjectEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SubjectQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.SubjectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectDao extends BaseMapper<SubjectEntity> {
    List<SubjectVO> query(Page page, @Param("query") SubjectQueryForm query);
}
