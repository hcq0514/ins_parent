package com.ins.moment.dao;

import com.ins.model.moment.Moment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Repository
public interface MomentDao extends JpaRepository<Moment, String> {
    List<Moment> getByUserId(String userId);

    List<Moment> getByIdIn(List ids);

    List<Moment> getByUserIdIn(List userIds);

}
