package com.ins.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.UserExceptionCode;
import com.ins.common.result.CommonResult;
import com.ins.common.util.JwtUtil;
import com.ins.model.base.Follow;
import com.ins.model.user.User;
import com.ins.user.client.FollowClient;
import com.ins.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    FollowClient followClient;

    public User register(User user) {
        return userDao.save(user);
    }

    //todo 密码使用加密
    public Map login(String email, String password) {
        Optional<User> userOptional = userDao.getByEmail(email);
        if (!userOptional.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            ExceptionCast.cast(UserExceptionCode.PASSWORD_ERROR);
        }
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSONObject.toJSONString(user), 100000);
        Map map = new HashMap();
        map.put("userId",user);
        map.put("username",user);
        map.put("userBio",user);
        map.put("userPhoto",user);
        map.put("token",token);
        return map;
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
        CommonResult<List<Follow>> followList1 = followClient.getFollowList(id);
        List<Follow> followList = followList1.getData();
        StringBuilder sb = new StringBuilder();
        followList.stream()
                .map(Follow::getTargetUserId)
                .forEach(x -> sb.append(x).append(","));
        ArrayList<String> ids = new ArrayList<>();
        Collections.addAll(ids, sb.substring(0, sb.length() - 1).split(","));
        return userDao.getByIdIn(ids);
    }

    public List<User> getFansListByUserId(String id) {
        //判断是否存在该用户
        getUserById(id);
        List<Follow> followList = followClient.getFansList(id).getData();
        StringBuilder sb = new StringBuilder();
        followList.stream()
                .map(Follow::getUserId)
                .forEach(x -> sb.append(x).append(","));
        ArrayList<String> ids = new ArrayList<>();
        Collections.addAll(ids, sb.substring(0, sb.length() - 1).split(","));
        return userDao.getByIdIn(ids);
    }
}
