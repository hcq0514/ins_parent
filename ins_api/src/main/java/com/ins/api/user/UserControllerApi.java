package com.ins.api.user;

import com.ins.common.result.CommonResult;
import com.ins.model.user.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@ApiOperation("用户接口")
@RestController
@RequestMapping("user")
public interface UserControllerApi {


    @ApiOperation("用户注册")
    @PostMapping("register")
    CommonResult register(@RequestBody User user);

    @ApiOperation("更新用户姓名与简介")
    @PutMapping("modifyUsernameAndBio")
    CommonResult modifyUsernameAndBio(@RequestParam(value = "id", required = false) String id,
                                      @RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "bio", required = false) String bio);

    @ApiOperation("用户登录")
    @PostMapping("login")
    CommonResult login(@RequestBody User user);

    @ApiOperation("修改密码")
    @PutMapping("modifyPassword")
    CommonResult modifyPassword(@RequestParam("email") String email,
                                @RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword);

    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    CommonResult getUserInfo(@PathVariable("id") String id);

    @ApiOperation("获取用户关注列表")
    @GetMapping("getFollowListByUserId")
    CommonResult getFollowListByUserId(@RequestParam("userId") String userId);

    @ApiOperation("获取用户粉丝列表")
    @GetMapping("getFansListByUserId")
    CommonResult getFansListByUserId(@RequestParam("userId") String userId);

}
