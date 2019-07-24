package com.ins.api.base;

import com.ins.common.result.CommonResult;
import com.ins.model.base.Follow;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ApiOperation("关注功能api")
@RequestMapping("follow")
public interface FollowControllerApi {

    @ApiOperation("添加关注")
    @PostMapping("addFollow")
    CommonResult addFollow(Follow follow);

    @ApiOperation("获取用户关注用户")
    @GetMapping("getFollowList")
    CommonResult<List<Follow>> getFollowList(@RequestParam("userId") String userId);

    @ApiOperation("获取用户粉丝")
    @GetMapping("getFansList")
    CommonResult<List<Follow>> getFansList(@RequestParam("userId") String userId);


    @ApiOperation("判断是否存在关注关系")
    @GetMapping("existFollowByUserIdAndTargetUserId")
    CommonResult existFollowByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetUserId") String targetUserId);

    @ApiOperation("删除关注")
    @GetMapping("deleteFollowByUserIdAndTargetUserId")
    CommonResult deleteFollowByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetUserId") String targetUserId);


}

