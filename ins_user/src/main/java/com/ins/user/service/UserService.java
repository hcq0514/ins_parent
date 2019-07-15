package com.ins.user.service;

import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.UserExceptionCode;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.user.User;
import com.ins.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public CommonResult register(User user) {
        User save = userDao.save(user);
        return new CommonResult<>(CommonCode.SUCCESS, save);
    }

    //todo 密码使用加密
    public CommonResult login(String email, String password) {
        Optional<User> userOptional = userDao.getByEmail(email);
        if (!userOptional.isPresent()){
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)){
            ExceptionCast.cast(UserExceptionCode.PASSWORD_ERROR);
        }
        return new CommonResult<>(CommonCode.SUCCESS, user);
    }

    public CommonResult modifyPassword(String email, String oldPassword, String newPassword) {
        Optional<User> userOptional = userDao.getByEmail(email);
        if (!userOptional.isPresent()){
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(oldPassword)){
            ExceptionCast.cast(UserExceptionCode.PASSWORD_ERROR);
        }
        user.setPassword(newPassword);
        userDao.save(user);
        return new CommonResult<>(CommonCode.SUCCESS, user);
    }

    public CommonResult getUserById(String id) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()){
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        return new CommonResult<>(CommonCode.SUCCESS, userOptional.get());
    }

    public CommonResult modifyUser(User user) {
        Optional<User> userOptional = userDao.findById(user.getId());
        if (!userOptional.isPresent()){
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        User update = userDao.save(user);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    public CommonResult getFansCountById(String id) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()){
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        return new CommonResult<>(CommonCode.SUCCESS, userOptional.get().getFansCount());
    }

    public CommonResult getFollowCountById(String id) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()){
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        return new CommonResult<>(CommonCode.SUCCESS, userOptional.get().getFollowCount());
    }
}
