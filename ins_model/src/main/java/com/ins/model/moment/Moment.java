package com.ins.model.moment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : hcq
 * @date : 2019/7/16
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "moment")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Moment implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ApiModelProperty("发表用户id")
    private String userId;
    @ApiModelProperty("动态图片存储数据的jsonURL")
    private String imgListUrl;
    @ApiModelProperty("动态图片")
    @Transient
    @JsonIgnore
    private List<String> imgList;
    @ApiModelProperty("动态内容")
    private String content;
    @ApiModelProperty("收藏数")
    private Integer collectNum;
    @ApiModelProperty("点赞数")
    private Integer likeNum;
    @ApiModelProperty("评论数")
    private Integer commentNum;
    @ApiModelProperty("转发数")
    private Integer forwardNum;
    @ApiModelProperty("从哪里转发")
    private String forwardFromUserId;
    @ApiModelProperty("从哪里转发")
    @Transient
    private String forwardFromUserName;
    @ApiModelProperty("从哪里转发")
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
