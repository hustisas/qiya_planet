package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.AlbumAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.AlbumQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.AlbumUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.AlbumVO;
import com.study.planet.wp.admin.module.business.qiya.service.AlbumService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class AlbumController {

    @Resource
    private AlbumService albumService;

    @Operation(summary = "分页查询磨耳朵")
    @PostMapping("/qiya/album/query")
    @SaCheckPermission("qiya:album:query")
    public ResponseDTO<PageResult<AlbumVO>> query(@RequestBody @Valid AlbumQueryForm queryForm) {
        return albumService.query(queryForm);
    }

    @Operation(summary = "添加磨耳朵")
    @PostMapping("/qiya/album/add")
    @SaCheckPermission("qiya:album:add")
    public ResponseDTO<String> add(@RequestBody @Valid AlbumAddForm addForm) {
        return albumService.add(addForm);
    }

    @Operation(summary = "更新磨耳朵")
    @PostMapping("/qiya/album/update")
    @SaCheckPermission("qiya:album:update")
    public ResponseDTO<String> update(@RequestBody @Valid AlbumUpdateForm updateForm) {
        return albumService.update(updateForm);
    }

    @Operation(summary = "删除磨耳朵")
    @GetMapping("/qiya/album/delete/{albumId}")
    @SaCheckPermission("qiya:album:delete")
    public ResponseDTO<String> delete(@PathVariable Long albumId) {
        return albumService.delete(albumId);
    }
}
