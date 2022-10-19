package com.shk.mall.controller;/**
 * @author: sunhengkang
 * @date:2022/10/18
 */

import com.github.pagehelper.PageInfo;
import com.shk.mall.common.ApiRestRespose;
import com.shk.mall.model.pojo.Product;
import com.shk.mall.model.request.ProductListReq;
import com.shk.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *@BelongsProject: mall
 *@BelongsPackage: com.shk.mall.controller
 *@Author: sunhaiyang
 *@CreateTime: 2022-10-18  14:10
 *@Description: TODO
 *@Version: 1.0
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("product/detail")
    @ApiOperation("商品详情")
    public ApiRestRespose detail(Integer id){
        Product detail = productService.detail(id);
        return ApiRestRespose.success(detail);
    }

    @GetMapping("product/list")
    @ApiOperation("商品搜索")
    public ApiRestRespose list(ProductListReq productListReq){
        PageInfo list = productService.list(productListReq);
        return ApiRestRespose.success(list);
    }
}
