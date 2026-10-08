package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.OpsAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.OpsQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.OpsUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.OpsVO;
import com.study.planet.wp.admin.module.business.qiya.service.OpsService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class OpsController {

    @Resource
    private OpsService opsService;

    @Operation(summary = "分页查询今日与节气")
    @PostMapping("/qiya/ops/query")
    @SaCheckPermission("qiya:ops:query")
    public ResponseDTO<PageResult<OpsVO>> query(@RequestBody @Valid OpsQueryForm queryForm) {
        return opsService.query(queryForm);
    }

    @Operation(summary = "添加今日与节气")
    @PostMapping("/qiya/ops/add")
    @SaCheckPermission("qiya:ops:add")
    public ResponseDTO<String> add(@RequestBody @Valid OpsAddForm addForm) {
        return opsService.add(addForm);
    }

    @Operation(summary = "更新今日与节气")
    @PostMapping("/qiya/ops/update")
    @SaCheckPermission("qiya:ops:update")
    public ResponseDTO<String> update(@RequestBody @Valid OpsUpdateForm updateForm) {
        return opsService.update(updateForm);
    }

    @Operation(summary = "删除今日与节气")
    @GetMapping("/qiya/ops/delete/{opsId}")
    @SaCheckPermission("qiya:ops:delete")
    public ResponseDTO<String> delete(@PathVariable Long opsId) {
        return opsService.delete(opsId);
    }
}
