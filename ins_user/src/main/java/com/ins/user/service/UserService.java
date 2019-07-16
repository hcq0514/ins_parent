package com.ins.user.service;

import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.UserExceptionCode;
import com.ins.model.user.User;
import com.ins.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User register(User user) {
        return userDao.save(user);
    }

    //todo 密码使用加密
    public User login(String email, String password) {
        Optional<User> userOptional = userDao.getByEmail(email);
        if (!userOptional.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            ExceptionCast.cast(UserExceptionCode.PASSWORD_ERROR);
        }
        return user;
    }

    public User modifyPassword(String email, String oldPassword, String newPassword) {
        Optional<User> userOptional = userDao.getByEmail(email);
        if (!userOptional.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(oldPassword)) {
            ExceptionCast.cast(UserExceptionCode.PASSWORD_ERROR);
        }
        user.setPassword(newPassword);
        userDao.save(user);
        return user;
    }

    public User getUserById(String id) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        return userOptional.get();
    }

    public User modifyUsernameAndBio(String id, String username, String bio) {
        User user = userDao.save(getUserById(id).setUsername(username).setBio(bio));
        return user;
    }

    public List<User> getFollowListByUserId(String id) {
        //判断是否存在该用户
        getUserById(id);
        return userDao.getFollowList(id);
    }

    public List<User> getFansListByUserId(String id) {
        //判断是否存在该用户
//        getUserById(id);
        return userDao.getFansList(id);
    }

}
