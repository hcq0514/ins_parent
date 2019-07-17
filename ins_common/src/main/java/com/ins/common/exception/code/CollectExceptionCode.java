package com.ins.common.exception.code;

import com.ins.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum CollectExceptionCode implements ResultCode {
    //collect异常从2201开始
    COLLECTION_NOT_EXIST(false, 2201, "收藏夹不存在"),
    COLLECTION_ALREADY_EXIST(false, 2202, "收藏夹已存在！"),
    COLLECTION_MOMENT_NOT_EXIST(false, 2203, "收藏夹记录不存在"),
    COLLECTION_MOMENT_ALREADY_EXIST(false, 2204, "收藏夹记录已存在！"),
    ;
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    CollectExceptionCode(boolean success, int code, String message) {
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
