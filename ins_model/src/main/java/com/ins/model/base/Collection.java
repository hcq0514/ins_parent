package com.ins.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏夹
 *
 * @author : hcq
 * @date : 2019/7/17
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "collection")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Collection implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("收藏夹名称")
    private String collectionName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
