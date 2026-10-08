package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.KnowledgeEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.KnowledgeQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.KnowledgeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KnowledgeDao extends BaseMapper<KnowledgeEntity> {
    List<KnowledgeVO> query(Page page, @Param("query") KnowledgeQueryForm query);
}
