package com.ins.collect.controller;

import com.ins.collect.service.CollectionMomentService;
import com.ins.collect.service.CollectionService;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.collect.Collection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author : hcq
 * @date : 2019/7/12
 */

@ApiOperation("收藏功能api")
@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    CollectionMomentService collectionMomentService;


    @ApiOperation("添加收藏夹")
    @PostMapping("addCollection")
    public CommonResult addCollection(@RequestParam("userId") String userId, @RequestParam("collectionName") String collectionName) {
        Collection collection = new Collection()
                .setUserId(userId)
                .setCollectionName(collectionName)
                .setCreateTime(LocalDateTime.now());
        return new CommonResult<>(CommonCode.SUCCESS, collectionService.saveCollection(collection));
    }


}
