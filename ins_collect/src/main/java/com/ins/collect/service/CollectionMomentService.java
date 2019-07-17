package com.ins.collect.service;

import com.ins.collect.dao.CollectionMomentDao;
import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.CollectExceptionCode;
import com.ins.model.collect.CollectionMoment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class CollectionMomentService {

    @Autowired
    CollectionMomentDao collectionDao;

    public CollectionMoment getCollectionMomentById(String id) {
        Optional<CollectionMoment> collectionOptional = collectionDao.findById(id);
        if (!collectionOptional.isPresent()) {
            ExceptionCast.cast(CollectExceptionCode.COLLECTION_MOMENT_NOT_EXIST);
        }
        return collectionOptional.get();
    }


    public CollectionMoment saveCollectionMoment(CollectionMoment collection) {
        return collectionDao.save(collection);
    }

    public CollectionMoment updateCollectionMoment(CollectionMoment collection) {
        getCollectionMomentById(collection.getId());
        return collectionDao.save(collection);
    }

    public void deleteCollectionMomentById(String id) {
        getCollectionMomentById(id);
        collectionDao.deleteById(id);
    }

}
