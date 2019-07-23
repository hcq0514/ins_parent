package com.ins.model.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 关注
 *
 * @author : hcq
 * @date : 2019/7/23
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "follow")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Follow implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("被关注的用户id")
    private String targetUserId;
    @ApiModelProperty("状态")
    private Boolean status;

    @ApiModelProperty(value = "...", hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "...", hidden = true)
    private LocalDateTime updateTime;

}
