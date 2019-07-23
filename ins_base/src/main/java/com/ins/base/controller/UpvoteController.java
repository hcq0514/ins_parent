package com.ins.base.controller;

import com.ins.base.service.UpvoteService;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.base.Upvote;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@ApiOperation("点赞功能api")
@RestController
@RequestMapping("like")
public class UpvoteController {

    @Autowired
    UpvoteService upvoteService;


    @ApiOperation("添加点赞")
    @PostMapping("addUpvote")
    public CommonResult addUpvote(Upvote upvote) {
        upvote.setCreateTime(LocalDateTime.now());
        upvote.setUpdateTime(LocalDateTime.now());
        upvote.setStatus(true);
        Upvote saveUpvote = upvoteService.saveUpvote(upvote);
        return new CommonResult<>(CommonCode.SUCCESS, saveUpvote);
    }

    @ApiOperation("判断是否已关注")
    @GetMapping("existUpvoteByUserIdAndTargetUserId")
    public CommonResult existUpvoteByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetMomentId") String targetMomentId) {
        return new CommonResult<>(CommonCode.SUCCESS, upvoteService.existUpvoteByUserIdAndTargetUserId(userId, targetMomentId));
    }

    @ApiOperation("删除关注")
    @GetMapping("deleteUpvoteByUserIdAndTargetUserId")
    public CommonResult deleteUpvoteByUserIdAndTargetUserId(@RequestParam("userId") String userId, @RequestParam("targetMomentId") String targetMomentId) {
        upvoteService.deleteUpvoteByUserIdAndTargetUserId(userId, targetMomentId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }


}
