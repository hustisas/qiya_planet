package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.WordEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WordQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WordDao extends BaseMapper<WordEntity> {
    List<WordVO> query(Page page, @Param("query") WordQueryForm query);
}
