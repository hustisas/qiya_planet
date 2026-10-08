package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoetAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoetQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoetUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.PoetVO;
import com.study.planet.wp.admin.module.business.qiya.service.PoetService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class PoetController {

    @Resource
    private PoetService poetService;

    @Operation(summary = "分页查询诗人馆")
    @PostMapping("/qiya/poet/query")
    @SaCheckPermission("qiya:poet:query")
    public ResponseDTO<PageResult<PoetVO>> query(@RequestBody @Valid PoetQueryForm queryForm) {
        return poetService.query(queryForm);
    }

    @Operation(summary = "添加诗人馆")
    @PostMapping("/qiya/poet/add")
    @SaCheckPermission("qiya:poet:add")
    public ResponseDTO<String> add(@RequestBody @Valid PoetAddForm addForm) {
        return poetService.add(addForm);
    }

    @Operation(summary = "更新诗人馆")
    @PostMapping("/qiya/poet/update")
    @SaCheckPermission("qiya:poet:update")
    public ResponseDTO<String> update(@RequestBody @Valid PoetUpdateForm updateForm) {
        return poetService.update(updateForm);
    }

    @Operation(summary = "删除诗人馆")
    @GetMapping("/qiya/poet/delete/{poetId}")
    @SaCheckPermission("qiya:poet:delete")
    public ResponseDTO<String> delete(@PathVariable Long poetId) {
        return poetService.delete(poetId);
    }

    @Operation(summary = "预览讲解")
    @GetMapping("/qiya/poet/preview/{poetId}")
    @SaCheckPermission("qiya:poet:query")
    public ResponseDTO<String> preview(@PathVariable Long poetId) {
        return poetService.preview(poetId);
    }
}
