package com.ins.collect.service;

import com.ins.collect.dao.CollectionDao;
import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.CollectExceptionCode;
import com.ins.model.collect.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class CollectionService {

    @Autowired
    CollectionDao collectionDao;

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

}
