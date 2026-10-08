package com.study.planet.wp.admin.module.business.qiya.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.study.planet.wp.admin.constant.AdminSwaggerTagConst;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ChannelAddForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ChannelQueryForm;
import com.study.planet.wp.admin.module.business.qiya.domain.form.ChannelUpdateForm;
import com.study.planet.wp.admin.module.business.qiya.domain.vo.ChannelVO;
import com.study.planet.wp.admin.module.business.qiya.service.ChannelService;
import com.study.planet.wp.base.common.domain.PageResult;
import com.study.planet.wp.base.common.domain.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = AdminSwaggerTagConst.Business.QIYA)
public class ChannelController {

    @Resource
    private ChannelService channelService;

    @Operation(summary = "分页查询推广看板")
    @PostMapping("/qiya/channel/query")
    @SaCheckPermission("qiya:channel:query")
    public ResponseDTO<PageResult<ChannelVO>> query(@RequestBody @Valid ChannelQueryForm queryForm) {
        return channelService.query(queryForm);
    }

    @Operation(summary = "添加推广看板")
    @PostMapping("/qiya/channel/add")
    @SaCheckPermission("qiya:channel:add")
    public ResponseDTO<String> add(@RequestBody @Valid ChannelAddForm addForm) {
        return channelService.add(addForm);
    }

    @Operation(summary = "更新推广看板")
    @PostMapping("/qiya/channel/update")
    @SaCheckPermission("qiya:channel:update")
    public ResponseDTO<String> update(@RequestBody @Valid ChannelUpdateForm updateForm) {
        return channelService.update(updateForm);
    }

    @Operation(summary = "删除推广看板")
    @GetMapping("/qiya/channel/delete/{channelId}")
    @SaCheckPermission("qiya:channel:delete")
    public ResponseDTO<String> delete(@PathVariable Long channelId) {
        return channelService.delete(channelId);
    }
}
