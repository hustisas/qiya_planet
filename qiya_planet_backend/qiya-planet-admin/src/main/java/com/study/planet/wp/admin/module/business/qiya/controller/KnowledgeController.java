package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.KnowledgeAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.KnowledgeQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.KnowledgeUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.KnowledgeVO;
import com.study.planet.wp.admin.module.business.qiya.service.KnowledgeService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class KnowledgeController {

    @Resource
    private KnowledgeService knowledgeService;

    @Operation(summary = "分页查询知识点")
    @PostMapping("/qiya/knowledge/query")
    @SaCheckPermission("qiya:knowledge:query")
    public ResponseDTO<PageResult<KnowledgeVO>> query(@RequestBody @Valid KnowledgeQueryForm queryForm) {
        return knowledgeService.query(queryForm);
    }

    @Operation(summary = "添加知识点")
    @PostMapping("/qiya/knowledge/add")
    @SaCheckPermission("qiya:knowledge:add")
    public ResponseDTO<String> add(@RequestBody @Valid KnowledgeAddForm addForm) {
        return knowledgeService.add(addForm);
    }

    @Operation(summary = "更新知识点")
    @PostMapping("/qiya/knowledge/update")
    @SaCheckPermission("qiya:knowledge:update")
    public ResponseDTO<String> update(@RequestBody @Valid KnowledgeUpdateForm updateForm) {
        return knowledgeService.update(updateForm);
    }

    @Operation(summary = "删除知识点")
    @GetMapping("/qiya/knowledge/delete/{knowledgeId}")
    @SaCheckPermission("qiya:knowledge:delete")
    public ResponseDTO<String> delete(@PathVariable Long knowledgeId) {
        return knowledgeService.delete(knowledgeId);
    }
}
