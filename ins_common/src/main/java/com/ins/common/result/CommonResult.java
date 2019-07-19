package com.ins.common.result;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author : hcq
 * @date : 2019/5/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonResult<T> extends ResponseResult {
    T data;

    public CommonResult(ResultCode resultCode, T data) {
        super(resultCode);
        this.data = data;
    }

    public CommonResult(T data) {
        this.data = data;
    }

    public CommonResult() {
    }
}
