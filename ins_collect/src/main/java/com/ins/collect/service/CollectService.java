package com.ins.collect.service;

import com.ins.api.moment.MomentControllerApi;
import com.ins.collect.client.MomentClient;
import com.ins.collect.dao.CollectionDao;
import com.ins.collect.dao.CollectionMomentDao;
import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.CollectExceptionCode;
import com.ins.common.result.CommonResult;
import com.ins.model.collect.Collection;
import com.ins.model.collect.CollectionMoment;
import com.ins.model.collect.UserCollectionDto;
import com.ins.model.moment.Moment;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class CollectService {

    @Autowired
    MomentClient momentClient;


    @Autowired
    CollectionDao collectionDao;

    @Autowired
    CollectionMomentDao collectionMomentDao;

    public CollectionMoment getCollectionMomentById(String id) {
        Optional<CollectionMoment> collectionOptional = collectionMomentDao.findById(id);
        if (!collectionOptional.isPresent()) {
            ExceptionCast.cast(CollectExceptionCode.COLLECTION_MOMENT_NOT_EXIST);
        }
        return collectionOptional.get();
    }


    public CollectionMoment saveCollectionMoment(CollectionMoment collection) {
        return collectionMomentDao.save(collection);
    }

    public CollectionMoment updateCollectionMoment(CollectionMoment collection) {
        getCollectionMomentById(collection.getId());
        return collectionMomentDao.save(collection);
    }

    public void deleteCollectionMomentById(String id) {
        getCollectionMomentById(id);
        collectionMomentDao.deleteById(id);
    }

    public Collection getCollectionById(String id) {
        Optional<Collection> collectionOptional = collectionDao.findById(id);
        if (!collectionOptional.isPresent()) {
            ExceptionCast.cast(CollectExceptionCode.COLLECTION_NOT_EXIST);
        }
        return collectionOptional.get();
    }


    public Collection saveCollection(Collection collection) {
        return collectionDao.save(collection);
    }

    public Collection updateCollection(Collection collection) {
        getCollectionById(collection.getId());
        return collectionDao.save(collection);
    }

    public void deleteCollectionById(String id) {
        getCollectionById(id);
        collectionDao.deleteById(id);
    }


    public List<Moment> getCollectionMoment(String collectionId) {
        List<CollectionMoment> collectionMoments = collectionMomentDao.getByCollectionId(collectionId);
        List<String> collect = collectionMoments.stream().map(CollectionMoment::getMomentId).collect(Collectors.toList());
        CommonResult momentByIds = momentClient.getMomentByIds(collect);
        return null;
    }

    public List getUserCollections(String userId) {
        List<Collection> userCollections = collectionDao.getByUserId(userId);
        List<UserCollectionDto> userCollectionDtos = new ArrayList<>();
        for (Collection collection : userCollections) {
            UserCollectionDto userCollectionDto = new UserCollectionDto()
                    .setCollectionId(collection.getId())
                    .setCollectionName(collection.getCollectionName());
            //获取收藏夹里的收藏数
            Integer collectionMomentCount = collectionMomentDao.getCollectionMomentCount(collection.getId());
            userCollectionDto.setCollectNum(collectionMomentCount);
            userCollectionDtos.add(userCollectionDto);
        }
        return userCollectionDtos;
    }
}
