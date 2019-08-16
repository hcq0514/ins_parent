package com.ins.message.dao;

import com.ins.model.message.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface EmailDao extends JpaRepository<Email, String> {
}
