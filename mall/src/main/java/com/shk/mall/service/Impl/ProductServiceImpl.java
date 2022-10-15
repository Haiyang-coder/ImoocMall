package com.shk.mall.service.Impl;/**
 * @author: sunhengkang
 * @date:2022/10/15
 */

import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.dao.ProductMapper;
import com.shk.mall.model.pojo.Product;
import com.shk.mall.model.request.AddProductRequst;
import com.shk.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@BelongsProject: mall
 *@BelongsPackage: com.shk.mall.service.Impl
 *@Author: sunhaiyang
 *@CreateTime: 2022-10-15  14:36
 *@Description: TODO
 *@Version: 1.0
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public void addProduct(AddProductRequst addProductRequst) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductRequst,product);
        Product productOld = productMapper.selectByName(addProductRequst.getName());
        if (productOld != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_NAME_EXIST);
        }
        int count  = productMapper.insertSelective(product);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_INSERT);
        }
    }
}
