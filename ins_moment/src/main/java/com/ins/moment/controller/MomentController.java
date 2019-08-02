package com.ins.moment.controller;

import com.ins.api.moment.MomentControllerApi;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.moment.Comment;
import com.ins.model.moment.Moment;
import com.ins.model.moment.UserFollowListMomentVo;
import com.ins.moment.service.MomentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/12
 */


@RestController
@RequestMapping("moment")
public class MomentController implements MomentControllerApi {

    @Autowired
    MomentService momentService;


    @Override
    @ApiOperation("查询动态")
    @GetMapping("{id}")
    public CommonResult getMomentById(@PathVariable("id") String id) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.getMomentById(id));
    }

    @Override
    @ApiOperation("增加动态")
    @PostMapping
    public CommonResult saveMoment(@RequestBody Moment moment) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.saveMoment(moment));
    }

    @Override
    @ApiOperation("更新动态")
    @PutMapping
    public CommonResult updateMoment(@RequestBody Moment moment) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.updateMoment(moment));
    }

    @Override
    @ApiOperation("删除动态")
    @DeleteMapping
    public CommonResult deleteMoment(@RequestParam("id") String id) {
        momentService.deleteMomentById(id);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    @ApiOperation("查询用户动态")
    @GetMapping("getMomentsByUserId")
    public CommonResult getMomentsByUserId(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.getCommentsByUserId(userId));
    }

    @Override
    @ApiOperation("查看具体动态")
    @GetMapping("detail")
    public CommonResult getCommentsByUserId(@RequestParam("userId") String userId, @RequestParam("momentId") String momentId) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.getMomentDetail(userId, momentId));
    }

    @Override
    public CommonResult getMomentByIds(@RequestParam("ids") String ids) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.getMomentByIds(ids));
    }

    @Override
    @GetMapping("followUserMoments")
    public CommonResult<List<UserFollowListMomentVo>> followUserListMoments(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, momentService.followUserListMoments(userId));
    }

    @Override
    @PostMapping("addMomentComment")
    public CommonResult addMomentComment(@RequestParam("userId") String userId, @RequestParam("momentId") String momentId,@RequestParam("content") String content) {
        Comment comment = new Comment().setUserId(userId).setMomentId(momentId).setContent(content);
        return new CommonResult<>(CommonCode.SUCCESS, momentService.addMomentComment(comment));
    }


}
