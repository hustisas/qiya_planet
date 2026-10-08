package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WorldAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WorldQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WorldUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WorldVO;
import com.study.planet.wp.admin.module.business.qiya.service.WorldService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class WorldController {

    @Resource
    private WorldService worldService;

    @Operation(summary = "分页查询闯关世界")
    @PostMapping("/qiya/world/query")
    @SaCheckPermission("qiya:world:query")
    public ResponseDTO<PageResult<WorldVO>> query(@RequestBody @Valid WorldQueryForm queryForm) {
        return worldService.query(queryForm);
    }

    @Operation(summary = "添加闯关世界")
    @PostMapping("/qiya/world/add")
    @SaCheckPermission("qiya:world:add")
    public ResponseDTO<String> add(@RequestBody @Valid WorldAddForm addForm) {
        return worldService.add(addForm);
    }

    @Operation(summary = "更新闯关世界")
    @PostMapping("/qiya/world/update")
    @SaCheckPermission("qiya:world:update")
    public ResponseDTO<String> update(@RequestBody @Valid WorldUpdateForm updateForm) {
        return worldService.update(updateForm);
    }

    @Operation(summary = "删除闯关世界")
    @GetMapping("/qiya/world/delete/{worldId}")
    @SaCheckPermission("qiya:world:delete")
    public ResponseDTO<String> delete(@PathVariable Long worldId) {
        return worldService.delete(worldId);
    }
}
