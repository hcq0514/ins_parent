package com.ins.moment.service;

import com.alibaba.fastjson.JSON;
import com.ins.model.moment.Moment;
import com.ins.model.user.User;
import com.ins.moment.client.UserClient;
import com.ins.moment.dao.MomentDao;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MomentServiceTest {
    @Autowired
    UserClient userClient;

    @Autowired
    MomentDao momentDao;

    @Test
    public void testPhotoJson() {
        List<String> photoUrl = new ArrayList<>();
        photoUrl.add("hello1");
        photoUrl.add("hello2");
        photoUrl.add("hello3");
        photoUrl.add("hello4");
        String photo = JSON.toJSONString(photoUrl);
        System.out.println("photo = " + photo);
        List list = JSON.parseObject(photo, List.class);
        System.out.println("list = " + list);
    }


    @Test
    public void testUserClient() {
        userClient.getFansListByUserId("1825");
    }

    @Test
    public void testJpaClient() {
//        Page<User> UserPage = momentDao.findAll(new Specification() {
//            @Override
//            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                Predicate predicate = criteriaBuilder.conjunction();
//                if (StringUtils.isNotBlank(database)){
//                    predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("name"),database));
//                }
//                if (StringUtils.isNotBlank(search)){
//                    predicate = criteriaBuilder.and(predicate,criteriaBuilder.or(criteriaBuilder.like(root.get("email"),search),
//                            criteriaBuilder.like(root.get("phone"),"%"+search+"%")));
//                }
//                return predicate;
//            }
//        },pageRequest);
//        userClient.getFansListByUserId("1825");
    }

    @Test
    public void testMomentDao(){
        List<Moment> all = momentDao.findAll();
        System.out.println(all);
    }

    @Test
    public void testPage(){
        List userIds = new ArrayList();
        userIds.add("1");
        Pageable pageable = PageRequest.of(0, 10);
        List<Moment> moments = momentDao.getByUserIdIn(userIds,pageable);
        System.out.println("moments = " + moments);
    }



}
