package com.ins.moment.service;

import com.ins.common.exception.ExceptionCast;
import com.ins.common.exception.code.MomentExceptionCode;
import com.ins.common.result.CommonResult;
import com.ins.model.moment.Moment;
import com.ins.model.moment.UserFollowListMomentVo;
import com.ins.model.user.User;
import com.ins.moment.client.UserClient;
import com.ins.moment.dao.MomentDao;
import com.ins.moment.dto.MomentDetailDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Service
public class MomentService {

    @Autowired
    UserClient userClient;

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

    public MomentDetailDto getMomentDetail(String userId, String momentId) {
        MomentDetailDto momentDetailDto = new MomentDetailDto();
        Moment moment = getMomentById(momentId);
        BeanUtils.copyProperties(moment, momentDetailDto);
        momentDetailDto.setUsername("hcq");
        momentDetailDto.setUserHeadImg("https://i.ibb.co/515PkG6/20190715145133.jpg");
        return momentDetailDto;
    }

    public List getMomentByIds(String ids) {
        ArrayList<String> objects = new ArrayList<>();
        Collections.addAll(objects, ids.split(","));
        return momentDao.getByIdIn(objects);
    }

    public List<UserFollowListMomentVo> followUserListMoments(String userId) {
        //1.获取用户关注人
        CommonResult<List<User>> data = userClient.getFollowListByUserId(userId);
        StringBuilder sb = new StringBuilder();
        data.getData().stream()
                .map(User::getId)
                .forEach(x -> sb.append(x).append(","));
        ArrayList<String> userIds = new ArrayList<>();
        Collections.addAll(userIds, sb.substring(0, sb.length() - 1).split(","));
        //2.获取关注人的最新动态 todo 这边到时候修改查询方式，增加限制等
        List<Moment> moments = momentDao.getByUserIdIn(userIds);
        //3.返回拼装数据
        List<UserFollowListMomentVo> list = new ArrayList<>();
        for (Moment moment : moments) {
            UserFollowListMomentVo momentVo = new UserFollowListMomentVo();
            //动态数据
            momentVo.setMomentId(moment.getId())
                    .setMomentCreateTime(moment.getCreateTime())
                    .setMomentContent(moment.getContent());
            //拼装用户数据
            User momentUser = userClient.getUserInfo(moment.getUserId()).getData();
            momentVo.setUserId(momentUser.getId())
                    .setUserBio(momentUser.getBio())
                    .setUserName(momentUser.getUsername())
                    .setUserPhotoUrl(momentUser.getPhotoUrl());
            //判断是否转发
            if (!StringUtils.isEmpty(moment.getForwardFromUserId() )) {
                momentVo.setForwardFromUserId(moment.getForwardFromUserId())
                        .setForwardFromUserName(userClient.getUserInfo(moment.getForwardFromUserId()).getData().getUsername());
            }
            list.add(momentVo);
            //todo 判断用户是否已经点赞，以及是否已经收藏 还有用户tags
        }
        return list;
    }
}
