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
@Table(name = "template")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Template {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @ApiModelProperty("第三方模板Id")
    private String channelTemplateId;

    @ApiModelProperty("模板名称")
    private String name;

    @ApiModelProperty("短信类型 0代表验证码，1代表通用")
    private Integer type;

    @ApiModelProperty("模板内容")
    private String content;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
