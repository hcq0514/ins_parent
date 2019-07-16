package com.ins.common.exception.code;

import com.ins.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum MomentExceptionCode implements ResultCode {
    //moment异常从2101开始
    MOMENT_NOT_EXIST(false, 2101, "用户不存在"),
    MOMENT_ALREADY_EXIST(false, 2102, "用户已存在！"),
    ;
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    MomentExceptionCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
