package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoemAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoemQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.PoemUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.PoemVO;
import com.study.planet.wp.admin.module.business.qiya.service.PoemService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class PoemController {

    @Resource
    private PoemService poemService;

    @Operation(summary = "分页查询诗词库")
    @PostMapping("/qiya/poem/query")
    @SaCheckPermission("qiya:poem:query")
    public ResponseDTO<PageResult<PoemVO>> query(@RequestBody @Valid PoemQueryForm queryForm) {
        return poemService.query(queryForm);
    }

    @Operation(summary = "添加诗词库")
    @PostMapping("/qiya/poem/add")
    @SaCheckPermission("qiya:poem:add")
    public ResponseDTO<String> add(@RequestBody @Valid PoemAddForm addForm) {
        return poemService.add(addForm);
    }

    @Operation(summary = "更新诗词库")
    @PostMapping("/qiya/poem/update")
    @SaCheckPermission("qiya:poem:update")
    public ResponseDTO<String> update(@RequestBody @Valid PoemUpdateForm updateForm) {
        return poemService.update(updateForm);
    }

    @Operation(summary = "删除诗词库")
    @GetMapping("/qiya/poem/delete/{poemId}")
    @SaCheckPermission("qiya:poem:delete")
    public ResponseDTO<String> delete(@PathVariable Long poemId) {
        return poemService.delete(poemId);
    }
}
