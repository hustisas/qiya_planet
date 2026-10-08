package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.StudentAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.StudentQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.StudentUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.StudentVO;
import com.study.planet.wp.admin.module.business.qiya.service.StudentService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class StudentController {

    @Resource
    private StudentService studentService;

    @Operation(summary = "分页查询学生与家庭")
    @PostMapping("/qiya/student/query")
    @SaCheckPermission("qiya:student:query")
    public ResponseDTO<PageResult<StudentVO>> query(@RequestBody @Valid StudentQueryForm queryForm) {
        return studentService.query(queryForm);
    }

    @Operation(summary = "添加学生与家庭")
    @PostMapping("/qiya/student/add")
    @SaCheckPermission("qiya:student:add")
    public ResponseDTO<String> add(@RequestBody @Valid StudentAddForm addForm) {
        return studentService.add(addForm);
    }

    @Operation(summary = "更新学生与家庭")
    @PostMapping("/qiya/student/update")
    @SaCheckPermission("qiya:student:update")
    public ResponseDTO<String> update(@RequestBody @Valid StudentUpdateForm updateForm) {
        return studentService.update(updateForm);
    }

    @Operation(summary = "删除学生与家庭")
    @GetMapping("/qiya/student/delete/{studentId}")
    @SaCheckPermission("qiya:student:delete")
    public ResponseDTO<String> delete(@PathVariable Long studentId) {
        return studentService.delete(studentId);
    }
}
