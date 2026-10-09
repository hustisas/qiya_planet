package com.study.planet.wp.admin.module.business.qiya.controller;

import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.SpeechAssessForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.SpeechAssessVO;
import com.study.planet.wp.admin.module.business.qiya.service.SpeechAssessService;
import com.study.planet.wp.base.common.annoation.NoNeedLogin;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class SpeechController {

    @Resource
    private SpeechAssessService speechAssessService;

    @Operation(summary = "跟读发音评测")
    @NoNeedLogin
    @PostMapping("/qiya/speech/assess")
    public ResponseDTO<SpeechAssessVO> assess(@RequestBody @Valid SpeechAssessForm form) {
        return speechAssessService.assess(form);
    }
}