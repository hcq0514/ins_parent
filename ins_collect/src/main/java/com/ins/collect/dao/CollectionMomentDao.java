package com.ins.collect.dao;

import com.ins.model.collect.CollectionMoment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Repository
public interface CollectionMomentDao extends JpaRepository<CollectionMoment, String> {
}
