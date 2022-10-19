package com.shk.mall.controller;/**
 * @author: sunhengkang
 * @date:2022/10/15
 */

import com.github.pagehelper.PageInfo;
import com.shk.mall.common.ApiRestRespose;
import com.shk.mall.common.MallCommon;
import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.pojo.Product;
import com.shk.mall.model.request.AddProductRequst;
import com.shk.mall.model.request.UpdateProductReq;
import com.shk.mall.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

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
    @ApiOperation("添加商品")
    public ApiRestRespose addProduct(@RequestBody @Valid AddProductRequst addProductRequst){
        productService.addProduct(addProductRequst);
        return ApiRestRespose.success();
    }


    @ResponseBody
    @PostMapping("/admin/upload/file")
    @ApiOperation("上传图片")
    public ApiRestRespose upLoadFile(HttpServletRequest request,
                                     @RequestParam("file") MultipartFile file){
        //获取新的文件名字
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + fileType;

        //将文件转存到指定的路径中
        File fileMkir = new File(MallCommon.FILE_SRC);
        File filePic = new File(MallCommon.FILE_SRC + "/" + newFileName);

        if (!fileMkir.exists()) {
            if(!fileMkir.mkdir()){
                throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_IO_FILE_MAKE);
            }
        }
        try {
            file.transferTo(filePic);
        } catch (IOException e) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_IO_FILE_MAKE);
        }
        try {
            return ApiRestRespose
                    .success(getHost(new URI(request.getRequestURL() + "")) + "/images/"
                            + newFileName);
        } catch (URISyntaxException e) {
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_UPLOAD);
        }
    }

    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    @ResponseBody
    @PostMapping("/admin/product/update")
    @ApiOperation("更新商品")
    public ApiRestRespose updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq){
        Product product = new Product();
        BeanUtils.copyProperties(updateProductReq,product);
        productService.updateProduct(product);
        return ApiRestRespose.success();
    }


    @ResponseBody
    @PostMapping("/admin/product/delete")
    @ApiOperation("删除商品")
    public ApiRestRespose updateProduct(Integer id){
        productService.deleteProduct(id);
        return ApiRestRespose.success();
    }

    @ApiOperation("后台批量上下架接口")
    @PostMapping("/admin/product/batchUpdateSellStatus")
    @ResponseBody
    public ApiRestRespose batchUpdateSellStatus(@RequestParam Integer[] ids, @RequestParam Integer sellStatus) {
        productService.batchUpdateSellStatus(ids, sellStatus);
        return ApiRestRespose.success();
    }

    @ApiOperation("后台商品列表接口")
    @GetMapping("/admin/product/list")
    @ResponseBody
    public ApiRestRespose list(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        PageInfo pageInfo = productService.listForAdmin(pageNum, pageSize);
        return ApiRestRespose.success(pageInfo);
    }
}
