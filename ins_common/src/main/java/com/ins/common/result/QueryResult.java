package com.ins.common.result;

import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * @author : hcq
 * @date : 2019/5/28
 */
@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
