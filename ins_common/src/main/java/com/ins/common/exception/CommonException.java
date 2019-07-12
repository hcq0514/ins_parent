package com.ins.common.exception;


import com.ins.common.result.ResultCode;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
public class CommonException extends RuntimeException {
    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        //异常信息为错误代码+异常信息
        super("错误代码：" + resultCode.code() + ",错误信息：" + resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }

}
