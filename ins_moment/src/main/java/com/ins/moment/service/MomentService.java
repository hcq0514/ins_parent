package com.ins.moment.service;

import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.MomentExceptionCode;
import com.ins.model.moment.Moment;
import com.ins.moment.dao.MomentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class MomentService {

    @Autowired
    MomentDao momentDao;

    public Moment getMomentById(String id) {
        Optional<Moment> momentOptional = momentDao.findById(id);
        if (!momentOptional.isPresent()) {
            ExceptionCast.cast(MomentExceptionCode.MOMENT_NOT_EXIST);
        }
        return momentOptional.get();
    }


    public Moment saveMoment(Moment moment) {
        return momentDao.save(moment);
    }

    public Moment updateMoment(Moment moment) {
        getMomentById(moment.getId());
        return momentDao.save(moment);
    }

    public void deleteMomentById(String id) {
        getMomentById(id);
        momentDao.deleteById(id);
    }

    public List getCommentsByUserId(String id) {
        return momentDao.getByUserId(id);
    }
}
