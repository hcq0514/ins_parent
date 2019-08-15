package com.ins.model.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : hcq
 * @date : 2019/7/18
 */
@Data
@Accessors(chain = true)
public class UserCollectionDto {
    private String collectionId;
    private String collectionName;
    private Integer collectNum;
}
