package com.ins.model.base;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author : hcq
 * @date : 2019/7/15
 */
@Data
@Accessors(chain = true)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
}
