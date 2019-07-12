package com.ins.common.exception;


import com.ins.common.result.ResponseResult;
import com.ins.common.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@RestControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 捕获 commonException异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseResult customException(CommonException e) {
        logger.error("catch exception : {}\r\nexception: ", e.getMessage(), e);
        ResultCode resultCode = e.getResultCode();
        return new ResponseResult(resultCode);
    }


}
