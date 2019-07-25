package com.ins.base.controller;

import com.ins.api.base.FollowControllerApi;
import com.ins.base.service.FollowService;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.base.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@RestController
@RequestMapping("follow")
public class FollowController implements FollowControllerApi {

    @Autowired
    FollowService followService;


    @Override
    @PostMapping("addFollow")
    public CommonResult addFollow(@RequestParam("userId") String userId, @RequestParam("targetUserId") String targetUserId) {
        Follow follow = new Follow()
                .setUserId(userId)
                .setTargetUserId(targetUserId);
        follow.setCreateTime(LocalDateTime.now());
        follow.setUpdateTime(LocalDateTime.now());
        follow.setStatus(true);
        return new CommonResult<>(CommonCode.SUCCESS, followService.saveFollow(follow));
    }

    @Override
    @GetMapping("getFollowList")
    public CommonResult<List<Follow>> getFollowList(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, followService.getFollowList(userId));
    }

    @Override
    @GetMapping("getFansList")
    public CommonResult<List<Follow>> getFansList(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, followService.getFansList(userId));
    }

    @Override
    @GetMapping("existFollowByUserIdAndTargetUserId")
    public CommonResult existFollowByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetUserId") String targetUserId) {
        return new CommonResult<>(CommonCode.SUCCESS, followService.existFollowByUserIdAndTargetUserId(userId, targetUserId));
    }

    @Override
    @GetMapping("deleteFollowByUserIdAndTargetUserId")
    public CommonResult deleteFollowByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetUserId") String targetUserId) {
        followService.deleteFollowByUserIdAndTargetUserId(userId, targetUserId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }


}
