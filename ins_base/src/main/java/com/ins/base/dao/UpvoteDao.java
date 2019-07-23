package com.ins.base.dao;

import com.ins.model.base.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Repository
public interface UpvoteDao extends JpaRepository<Upvote, String> {

    Boolean existsByUserIdAndTargetMomentIdAndStatus(String userId, String targetMomentId, boolean status);

    Upvote getByUserIdAndTargetMomentIdAndStatus(String userId, String targetMomentId, boolean status);
}
