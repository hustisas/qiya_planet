package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewDecideForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ReviewUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ReviewVO;
import com.study.planet.wp.admin.module.business.qiya.service.ReviewService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    @Operation(summary = "分页查询审核")
    @PostMapping("/qiya/review/query")
    @SaCheckPermission("qiya:review:query")
    public ResponseDTO<PageResult<ReviewVO>> query(@RequestBody @Valid ReviewQueryForm queryForm) {
        return reviewService.query(queryForm);
    }

    @Operation(summary = "添加审核")
    @PostMapping("/qiya/review/add")
    @SaCheckPermission("qiya:review:add")
    public ResponseDTO<String> add(@RequestBody @Valid ReviewAddForm addForm) {
        return reviewService.add(addForm);
    }

    @Operation(summary = "更新审核")
    @PostMapping("/qiya/review/update")
    @SaCheckPermission("qiya:review:update")
    public ResponseDTO<String> update(@RequestBody @Valid ReviewUpdateForm updateForm) {
        return reviewService.update(updateForm);
    }

    @Operation(summary = "删除审核")
    @GetMapping("/qiya/review/delete/{reviewId}")
    @SaCheckPermission("qiya:review:delete")
    public ResponseDTO<String> delete(@PathVariable Long reviewId) {
        return reviewService.delete(reviewId);
    }

    @Operation(summary = "通过或拒绝")
    @PostMapping("/qiya/review/decide")
    @SaCheckPermission("qiya:review:decide")
    public ResponseDTO<String> decide(@RequestBody @Valid ReviewDecideForm decideForm) {
        return reviewService.decide(decideForm);
    }
}
