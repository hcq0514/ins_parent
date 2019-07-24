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

    List<User> getByIdIn(List ids);
}
