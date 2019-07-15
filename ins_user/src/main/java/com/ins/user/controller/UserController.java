package com.ins.user.controller;

import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.user.User;
import com.ins.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@ApiOperation("用户接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @ApiOperation("用户注册")
    @PostMapping("register")
    public CommonResult register(@RequestBody User user) {
        //todo 根据邮箱和用户名判断是否存在该用户
        return new CommonResult<>(CommonCode.SUCCESS, userService.register(user));
    }

    @ApiOperation("更新用户姓名与简介")
    @PutMapping("modifyUsernameAndBio")
    public CommonResult modifyUsernameAndBio(@RequestParam(value = "id", required = false) String id,
                                             @RequestParam(value = "username", required = false) String username,
                                             @RequestParam(value = "bio", required = false) String bio) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.modifyUsernameAndBio(id, username, bio));
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public CommonResult login(@RequestParam("email") String email, @RequestParam("password") String password) {
        //todo 根据用户输入的邮箱或者名称来登录，这边只做了邮箱登录
        return new CommonResult<>(CommonCode.SUCCESS, userService.login(email, password));
    }

    @ApiOperation("修改密码")
    @PutMapping("modifyPassword")
    public CommonResult modifyPassword(String email, String oldPassword, String newPassword) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.modifyPassword(email, oldPassword, newPassword));
    }

    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    public CommonResult getUserInfo(@PathVariable("id") String id) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.getUserById(id));
    }

    @ApiOperation("获取用户信息")
    @GetMapping("followList")
    public CommonResult followList(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.getFollowList(userId));
    }

}
