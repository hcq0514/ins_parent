package com.ins.user.controller;

import com.ins.api.user.UserControllerApi;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.user.User;
import com.ins.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@ApiOperation("用户接口")
@RestController
@RequestMapping("user")
public class UserController implements UserControllerApi {

    @Autowired
    UserService userService;


    @Override
    @ApiOperation("用户注册")
    @PostMapping("register")
    public CommonResult register(@RequestBody User user) {
        //todo 根据邮箱和用户名判断是否存在该用户
        return new CommonResult<>(CommonCode.SUCCESS, userService.register(user));
    }

    @Override
    @ApiOperation("更新用户姓名与简介")
    @PutMapping("modifyUsernameAndBio")
    public CommonResult modifyUsernameAndBio(@RequestParam(value = "id", required = false) String id,
                                             @RequestParam(value = "username", required = false) String username,
                                             @RequestParam(value = "bio", required = false) String bio) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.modifyUsernameAndBio(id, username, bio));
    }

    @Override
    @ApiOperation("用户登录")
    @PostMapping("login")
    public CommonResult login(@RequestBody User user) {
        //todo 根据用户输入的邮箱或者名称来登录，这边只做了邮箱登录
        return new CommonResult<>(CommonCode.SUCCESS, userService.login(user.getEmail(), user.getPassword()));
    }

    @Override
    @ApiOperation("修改密码")
    @PutMapping("modifyPassword")
    public CommonResult modifyPassword(@RequestParam("email") String email,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.modifyPassword(email, oldPassword, newPassword));
    }

    @Override
    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    public CommonResult<User> getUserInfo(@PathVariable("id") String id) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.getUserById(id));
    }

    @Override
    @ApiOperation("获取用户关注列表")
    @GetMapping("getFollowListByUserId")
    public CommonResult<List<User>> getFollowListByUserId(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.getFollowListByUserId(userId));
    }

    @Override
    @ApiOperation("获取用户粉丝列表")
    @GetMapping("getFansListByUserId")
    public CommonResult getFansListByUserId(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, userService.getFansListByUserId(userId));
    }

}
