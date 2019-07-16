package com.ins.user.dao;

import com.ins.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {

    Optional<User> getByEmail(String email);

    @Query(value = "SELECT * from user where id in (SELECT target_user from follow where user_id = ?1)"
            , nativeQuery = true)
    List<User> getFollowList(String id);

    @Query(value = "SELECT * from user where id in (SELECT user_id  from follow where target_user = ?1)"
            , nativeQuery = true)
    List<User> getFansList(String id);
}
