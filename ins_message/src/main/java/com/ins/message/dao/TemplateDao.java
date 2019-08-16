package com.ins.message.dao;

import com.ins.model.message.Template;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface TemplateDao extends JpaRepository<Template, String> {
    Template getByChannelTemplateId(String channelTemplateId);
}
