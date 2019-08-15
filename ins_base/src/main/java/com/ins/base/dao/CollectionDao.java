package com.ins.base.dao;

import com.ins.model.base.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Repository
public interface CollectionDao extends JpaRepository<Collection, String> {
    List<Collection> getByUserId(String userId);
}
