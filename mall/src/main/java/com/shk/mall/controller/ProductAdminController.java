package com.shk.mall.controller;/**
 * @author: sunhengkang
 * @date:2022/10/15
 */

import com.shk.mall.common.ApiRestRespose;
import com.shk.mall.model.request.AddProductRequst;
import com.shk.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 *@BelongsProject: mall
 *@BelongsPackage: com.shk.mall.controller
 *@Author: sunhaiyang
 *@CreateTime: 2022-10-15  14:22
 *@Description: TODO
 *@Version: 1.0
 */
@Controller
public class ProductAdminController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @PostMapping("/admin/product/add")
    public ApiRestRespose addProduct(@RequestBody @Valid AddProductRequst addProductRequst){
        productService.addProduct(addProductRequst);
        return ApiRestRespose.success();
    }
}
