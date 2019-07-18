package com.ins.collect.dao;

import com.ins.model.collect.CollectionMoment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Repository
public interface CollectionMomentDao extends JpaRepository<CollectionMoment, String> {
    @Query(value = "SELECT count(0) from collection_moment where  collection_id = ?1"
            , nativeQuery = true)
    Integer getCollectionMomentCount(String collectionId);

    List<CollectionMoment> getByCollectionId(String collectionId);
}
