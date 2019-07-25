package com.ins.model.moment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/16
 */
@Data
@Accessors(chain = true)
public class UserFollowListMomentVo implements Serializable {

    @ApiModelProperty("发表动态的用户Id")
    private String userId;
    @ApiModelProperty("发表动态的用户名称")
    private String userName;
    @ApiModelProperty("发表动态的用户简介")
    private String userBio;
    @ApiModelProperty("发表动态的用户头像")
    private String userPhotoUrl;
    @ApiModelProperty("转出自用户id")
    private String forwardFromUserId;
    @ApiModelProperty("转出自用户名称")
    private Integer forwardFromUserName;
    @ApiModelProperty("动态id")
    private Integer momentId;
    @ApiModelProperty("动态内容")
    private Integer momentContent;
    @ApiModelProperty("动态创建时间")
    private Integer momentCreateTime;
    @ApiModelProperty("动态图片地址")
    //todo 后期图片全部改多图片存储
    private String momentImgList;
    @ApiModelProperty("标签")
    private List tags;
    @ApiModelProperty("评论")
    //todo 评论类 vo带用户名称的
    private List comments;
    @ApiModelProperty("是否已经点赞")
    private Boolean liked;
    @ApiModelProperty("是否已经收藏")
    private Boolean collected;
}
