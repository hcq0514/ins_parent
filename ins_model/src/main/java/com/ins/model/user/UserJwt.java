package com.ins.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : hcq
 * @date : 2019/7/12
 */
@Data
public class UserJwt implements Serializable {

    private String id;
    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("头像url")
    private String photoUrl;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("个人简介brief introduction")
    private String bio;
}
