package com.ins.moment.dto;

import com.ins.model.moment.Moment;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : hcq
 * @date : 2019/7/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MomentDetailDto extends Moment {
    private String username;
    private String userHeadImg;
    private String followState;
}
