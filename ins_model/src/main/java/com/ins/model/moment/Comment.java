package com.ins.model.moment;

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
 * @author : hcq
 * @date : 2019/7/16
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "comment")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ApiModelProperty("动态id")
    private String momentId;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty("发表评论的用户id")
    private String userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
