package com.ins.moment.controller;

import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.moment.Moment;
import com.ins.moment.service.MomentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@ApiOperation("动态接口")
@RestController
@RequestMapping("moment")
public class MomentController {

    @Autowired
    MomentService momentService;


    @ApiOperation("查询动态")
    @GetMapping("{id}")
    public CommonResult getUserInfo(@PathVariable("id") String id) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.getMomentById(id));
    }

    @ApiOperation("增加动态")
    @PostMapping
    public CommonResult saveMoment(@RequestBody Moment moment) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.saveMoment(moment));
    }

    @ApiOperation("更新动态")
    @PutMapping
    public CommonResult updateMoment(@RequestBody Moment moment) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.updateMoment(moment));
    }

    @ApiOperation("删除动态")
    @DeleteMapping
    public CommonResult deleteMoment(@RequestParam("id") String id) {
        momentService.deleteMomentById(id);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @ApiOperation("查询用户动态")
    @GetMapping("getCommentsByUserId")
    public CommonResult getCommentsByUserId(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.getCommentsByUserId(userId));
    }

}
