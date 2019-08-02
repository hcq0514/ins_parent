package com.ins.moment.dao;

import com.ins.model.moment.Comment;
import com.ins.model.moment.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, String> {
    List<Comment> getByMomentId(String momentId);
}
