package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SubjectAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SubjectQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SubjectUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.SubjectVO;
import com.study.planet.wp.admin.module.business.qiya.service.SubjectService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @Operation(summary = "分页查询科目")
    @PostMapping("/qiya/subject/query")
    @SaCheckPermission("qiya:subject:query")
    public ResponseDTO<PageResult<SubjectVO>> query(@RequestBody @Valid SubjectQueryForm queryForm) {
        return subjectService.query(queryForm);
    }

    @Operation(summary = "添加科目")
    @PostMapping("/qiya/subject/add")
    @SaCheckPermission("qiya:subject:add")
    public ResponseDTO<String> add(@RequestBody @Valid SubjectAddForm addForm) {
        return subjectService.add(addForm);
    }

    @Operation(summary = "更新科目")
    @PostMapping("/qiya/subject/update")
    @SaCheckPermission("qiya:subject:update")
    public ResponseDTO<String> update(@RequestBody @Valid SubjectUpdateForm updateForm) {
        return subjectService.update(updateForm);
    }

    @Operation(summary = "删除科目")
    @GetMapping("/qiya/subject/delete/{subjectId}")
    @SaCheckPermission("qiya:subject:delete")
    public ResponseDTO<String> delete(@PathVariable Long subjectId) {
        return subjectService.delete(subjectId);
    }

    @Operation(summary = "标记内容已齐")
    @GetMapping("/qiya/subject/ready/{subjectId}")
    @SaCheckPermission("qiya:subject:ready")
    public ResponseDTO<String> ready(@PathVariable Long subjectId) {
        return subjectService.markReady(subjectId);
    }

    @Operation(summary = "开放科目")
    @GetMapping("/qiya/subject/open/{subjectId}")
    @SaCheckPermission("qiya:subject:open")
    public ResponseDTO<String> open(@PathVariable Long subjectId) {
        return subjectService.open(subjectId);
    }
}
