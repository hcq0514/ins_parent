package com.ins.model.collect;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author : hcq
 * @date : 2019/7/17
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "collection_moment")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CollectionMoment {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ApiModelProperty("用户ID")
    private String collectionId;
    @ApiModelProperty("收藏夹名称")
    private String momentId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

