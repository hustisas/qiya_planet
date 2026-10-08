package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ResetAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ResetQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ResetUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ResetVO;
import com.study.planet.wp.admin.module.business.qiya.service.ResetService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class ResetController {

    @Resource
    private ResetService resetService;

    @Operation(summary = "分页查询重置与付费")
    @PostMapping("/qiya/reset/query")
    @SaCheckPermission("qiya:reset:query")
    public ResponseDTO<PageResult<ResetVO>> query(@RequestBody @Valid ResetQueryForm queryForm) {
        return resetService.query(queryForm);
    }

    @Operation(summary = "添加重置与付费")
    @PostMapping("/qiya/reset/add")
    @SaCheckPermission("qiya:reset:add")
    public ResponseDTO<String> add(@RequestBody @Valid ResetAddForm addForm) {
        return resetService.add(addForm);
    }

    @Operation(summary = "更新重置与付费")
    @PostMapping("/qiya/reset/update")
    @SaCheckPermission("qiya:reset:update")
    public ResponseDTO<String> update(@RequestBody @Valid ResetUpdateForm updateForm) {
        return resetService.update(updateForm);
    }

    @Operation(summary = "删除重置与付费")
    @GetMapping("/qiya/reset/delete/{resetId}")
    @SaCheckPermission("qiya:reset:delete")
    public ResponseDTO<String> delete(@PathVariable Long resetId) {
        return resetService.delete(resetId);
    }
}
