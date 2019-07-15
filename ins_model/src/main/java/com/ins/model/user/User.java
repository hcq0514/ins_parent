package com.ins.model.user;

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
 * @date : 2019/7/12
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User  {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("头像url")
    private String photoUrl;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("个人简介brief introduction")
    private String bio;
    @ApiModelProperty("粉丝数")
    private Integer fansCount;
    @ApiModelProperty("关注人数")
    private Integer followCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
