package com.ins.base.service;

import com.ins.base.dao.UpvoteDao;
import com.ins.model.base.Upvote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class UpvoteService {


    @Autowired
    UpvoteDao upvoteDao;

    //todo 点赞做redis处理
    public Upvote getUpvoteById(String id) {
        return upvoteDao.findById(id).get();
    }

    public Boolean existUpvoteByUserIdAndTargetUserId(String userId, String targetMomentId) {
        return upvoteDao.existsByUserIdAndTargetMomentIdAndStatus(userId, targetMomentId,true);
    }

    public Upvote saveUpvote(Upvote upvote) {
        return upvoteDao.save(upvote);
    }

    public void deleteUpvoteByUserIdAndTargetUserId(String userId, String targetMomentId) {
        Upvote upvote = upvoteDao.getByUserIdAndTargetMomentIdAndStatus(userId, targetMomentId, true);
        upvote.setStatus(false);
        upvote.setUpdateTime(LocalDateTime.now());
        upvoteDao.save(upvote);
    }
}
