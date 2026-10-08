package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.MemberSummaryVO;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.WorkbenchVO;
import com.study.planet.wp.admin.module.business.qiya.service.QiyaReportFacade;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class WorkbenchController {

    @Resource
    private QiyaReportFacade qiyaReportFacade;

    @Operation(summary = "工作台汇总")
    @GetMapping("/qiya/workbench/summary")
    @SaCheckPermission("qiya:workbench:query")
    public ResponseDTO<WorkbenchVO> summary() {
        return qiyaReportFacade.summary();
    }

    @Operation(summary = "家庭会员状态")
    @GetMapping("/qiya/member/summary")
    @SaCheckPermission("qiya:member:query")
    public ResponseDTO<MemberSummaryVO> member() {
        return qiyaReportFacade.member();
    }
}
