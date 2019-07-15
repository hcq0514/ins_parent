package com.ins.user.controller;

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

    /**
     * todo 根据邮箱和用户名判断是否存在该用户
     *
     * @param user
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("register")
    public CommonResult register(@RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation("用户更新")
    @PutMapping("update")
    public CommonResult modifyUser(@RequestBody User user) {
        return userService.modifyUser(user);
    }

    /**
     * todo 根据用户输入的邮箱或者名称来登录，这边只做了邮箱登录
     *
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("login")
    public CommonResult login(String email, String password) {
        return userService.login(email, password);
    }

    @ApiOperation("修改密码")
    @PutMapping("modifyPassword")
    public CommonResult modifyPassword(String email, String oldPassword, String newPassword) {
        return userService.modifyPassword(email, oldPassword, newPassword);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    public CommonResult getUserInfo(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }


    @ApiOperation("获取用户粉丝数")
    @GetMapping("getFansCount/{id}")
    public CommonResult getFansCount(@PathVariable("id") String id) {
        return userService.getFansCountById(id);
    }


    @ApiOperation("根据关注数")
    @GetMapping("getFollowCount/{id}")
    public CommonResult getFollowCount(@PathVariable("id") String id) {
        return userService.getFollowCountById(id);
    }


}
