package com.ins.common.exception.code;

import com.ins.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum UserExceptionCode implements ResultCode {
    //user异常从4001开始
    USER_NOT_EXIST(false, 4001, "用户不存在"),
    USER_ALREADY_EXIST(false, 4002, "用户已存在！"),
    //admin异常从4021开始
    ADMIN_NOT_EXIST(false, 4021, "管理员不存在"),
    ADMIN_ALREADY_EXIST(false, 4022, "管理员已存在！");
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    UserExceptionCode(boolean success, int code, String message) {
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
