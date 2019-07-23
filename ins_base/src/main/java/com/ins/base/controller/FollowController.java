package com.ins.base.controller;

import com.ins.base.service.FollowService;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.base.Follow;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@ApiOperation("收藏功能api")
@RestController
@RequestMapping("follow")
public class FollowController {

    @Autowired
    FollowService followService;


    @ApiOperation("添加关注")
    @PostMapping("addFollow")
    public CommonResult addCollection(Follow follow) {
        follow.setCreateTime(LocalDateTime.now());
        follow.setUpdateTime(LocalDateTime.now());
        follow.setStatus(true);
        return new CommonResult<>(CommonCode.SUCCESS, followService.saveFollow(follow));
    }

    @ApiOperation("判断是否已关注")
    @GetMapping("existFollowByUserIdAndTargetUserId")
    public CommonResult existFollowByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetUserId") String targetUserId) {
        return new CommonResult<>(CommonCode.SUCCESS, followService.existFollowByUserIdAndTargetUserId(userId, targetUserId));
    }

    @ApiOperation("删除关注")
    @GetMapping("deleteFollowByUserIdAndTargetUserId")
    public CommonResult deleteFollowByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetUserId") String targetUserId) {
        followService.deleteFollowByUserIdAndTargetUserId(userId, targetUserId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }


}
