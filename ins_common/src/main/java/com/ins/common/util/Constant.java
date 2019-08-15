package com.ins.common.util;

/**
 * @author : hcq
 * @date : 2019/8/9
 */
public class Constant
{
    /**
     * jwt
     *
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
}
