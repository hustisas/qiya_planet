package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WordAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WordQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.WordUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WordVO;
import com.study.planet.wp.admin.module.business.qiya.service.WordService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class WordController {

    @Resource
    private WordService wordService;

    @Operation(summary = "分页查询词库")
    @PostMapping("/qiya/word/query")
    @SaCheckPermission("qiya:word:query")
    public ResponseDTO<PageResult<WordVO>> query(@RequestBody @Valid WordQueryForm queryForm) {
        return wordService.query(queryForm);
    }

    @Operation(summary = "添加词库")
    @PostMapping("/qiya/word/add")
    @SaCheckPermission("qiya:word:add")
    public ResponseDTO<String> add(@RequestBody @Valid WordAddForm addForm) {
        return wordService.add(addForm);
    }

    @Operation(summary = "更新词库")
    @PostMapping("/qiya/word/update")
    @SaCheckPermission("qiya:word:update")
    public ResponseDTO<String> update(@RequestBody @Valid WordUpdateForm updateForm) {
        return wordService.update(updateForm);
    }

    @Operation(summary = "删除词库")
    @GetMapping("/qiya/word/delete/{wordId}")
    @SaCheckPermission("qiya:word:delete")
    public ResponseDTO<String> delete(@PathVariable Long wordId) {
        return wordService.delete(wordId);
    }
}
