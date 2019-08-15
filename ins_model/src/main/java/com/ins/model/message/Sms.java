package com.ins.model.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "sms")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Sms {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("短信参数")
    private String params;

    @ApiModelProperty("短信系统生成的验证码")
    private String validateCode;

    @ApiModelProperty("模版id")
    private String templateId;

    @ApiModelProperty("发送状态（0：失败，1：接口调用成功，2：发送成功）")
    private Integer sendStatus;

    @ApiModelProperty("短信内容")
    private String content;

    @ApiModelProperty("短信发送渠道")
    private String channel;

    @ApiModelProperty("返回内容")
    private String response;

    @ApiModelProperty("重试次数")
    private Integer retry;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
