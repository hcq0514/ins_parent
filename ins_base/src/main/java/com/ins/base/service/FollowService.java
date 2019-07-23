package com.ins.base.service;

import com.ins.base.client.MomentClient;
import com.ins.base.dao.FollowDao;
import com.ins.model.base.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class FollowService {

    @Autowired
    MomentClient momentClient;

    @Autowired
    FollowDao followDao;

    //todo 异常处理 到时候全部重构
    public Follow getFollowById(String id) {
        return followDao.findById(id).get();
    }

    public Boolean existFollowByUserIdAndTargetUserId(String userId, String targetUserId) {
        return followDao.existsByUserIdAndTargetUserIdAndStatus(userId, targetUserId,true);
    }

    public Follow saveFollow(Follow follow) {
        return followDao.save(follow);
    }

    public void deleteFollowByUserIdAndTargetUserId(String userId, String targetUserId) {
        Follow follow = followDao.getByUserIdAndTargetUserIdAndStatus(userId, targetUserId, true);
        follow.setStatus(false);
        followDao.save(follow);
    }
}
