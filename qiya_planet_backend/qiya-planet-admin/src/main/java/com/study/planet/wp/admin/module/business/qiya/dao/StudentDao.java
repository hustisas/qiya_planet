package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.StudentEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.StudentQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.StudentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDao extends BaseMapper<StudentEntity> {
    List<StudentVO> query(Page page, @Param("query") StudentQueryForm query);
}
