package com.study.planet.wp.admin.module.business.qiya.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.planet.wp.admin.module.business.qiya.domain.entity.AlbumEntity;
import com.study.planet.wp.admin.module.business.qiya.domain.form.AlbumQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.AlbumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlbumDao extends BaseMapper<AlbumEntity> {
    List<AlbumVO> query(Page page, @Param("query") AlbumQueryForm query);
}
