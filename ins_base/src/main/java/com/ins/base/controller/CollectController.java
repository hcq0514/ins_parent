package com.ins.base.controller;

import com.ins.base.service.CollectService;
import com.ins.common.result.CommonCode;
import com.ins.common.result.CommonResult;
import com.ins.model.base.Collection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    CollectService collectService;


    @ApiOperation("添加收藏夹")
    @GetMapping("addCollection")
    public CommonResult addCollection(@RequestParam("userId") String userId, @RequestParam("collectionName") String collectionName) {
        Collection collection = new Collection()
                .setUserId(userId)
                .setCollectionName(collectionName)
                .setCreateTime(LocalDateTime.now());
        return new CommonResult<>(CommonCode.SUCCESS, collectService.saveCollection(collection));
    }

    @ApiOperation("获取用户收藏夹")
    @GetMapping("getUserCollectionsByUserId")
    public CommonResult getUserCollections(@RequestParam("userId") String userId) {
        return new CommonResult<>(CommonCode.SUCCESS, collectService.getUserCollections(userId));
    }

    @ApiOperation("获取收藏夹里的动态")
    @GetMapping("getCollectionMoment")
    public CommonResult getCollectionMoment(@RequestParam("collectionId") String collectionId) {
        return new CommonResult<>(CommonCode.SUCCESS, collectService.getCollectionMoment(collectionId));
    }

    @ApiOperation("删除收藏夹")
    @GetMapping("deleteCollectionByCollectionId")
    public CommonResult deleteCollectionByCollectionId(@RequestParam("collectionId") String collectionId) {
        collectService.deleteCollectionByCollectionId(collectionId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @ApiOperation("重命名收藏夹")
    @GetMapping("renameCollection")
    public CommonResult renameCollection(@RequestParam("collectionId") String collectionId, @RequestParam("newName") String newName) {
        return new CommonResult<>(CommonCode.SUCCESS, collectService.renameCollection(collectionId, newName));
    }


}
