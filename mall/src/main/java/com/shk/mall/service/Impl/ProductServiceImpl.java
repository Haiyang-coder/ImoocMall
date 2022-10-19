package com.shk.mall.service.Impl;/**
 * @author: sunhengkang
 * @date:2022/10/15
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shk.mall.common.MallCommon;
import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.dao.ProductMapper;
import com.shk.mall.model.pojo.Product;
import com.shk.mall.model.query.ProductListQuery;
import com.shk.mall.model.request.AddProductRequst;
import com.shk.mall.model.request.ProductListReq;
import com.shk.mall.model.vo.CategoryVO;
import com.shk.mall.service.CategoryService;
import com.shk.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    CategoryService categoryService;

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

    @Override
    public void updateProduct(Product product) {
        Product product1 = productMapper.selectByName(product.getName());
        if(product1 != null && !product1.getId().equals(product.getId())){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_NAME_EXIST);
        }
        int i = productMapper.updateByPrimaryKeySelective(product);
        if (i == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_UPDATE);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        if (product == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_DELETE);
        }
        int i = productMapper.deleteByPrimaryKey(id);
        if (i == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_DELETE);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus) {
        productMapper.batchUpdateSellStatus(ids, sellStatus);
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectListForAdmin();
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    }

    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        if (product == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_NOT_EXIST);
        }
        return product;
    }

    @Override
    public PageInfo list(ProductListReq productListReq) {
        ProductListQuery productListQuery = new ProductListQuery();

        if(productListReq.getKeyword() != null){
            StringBuilder stringBuilder = new StringBuilder();
            String s = stringBuilder.append("%")
                    .append(productListReq.getKeyword())
                    .append("%").toString();
            productListQuery.setKeyword(s);
        }

        if(productListReq.getCategoryId() != null){
            ArrayList<Integer> integers = new ArrayList<>();
            List<CategoryVO> categoryVOS = categoryService.listForUser(productListReq.getCategoryId());
            getListArry(categoryVOS, integers);
            productListQuery.setCategoryIds(integers);
        }
        //排序处理
        String orderBy = productListReq.getOrderBy();
        if (MallCommon.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
            PageHelper
                    .startPage(productListReq.getPageNum(), productListReq.getPageSize(), orderBy);
        } else {
            PageHelper
                    .startPage(productListReq.getPageNum(), productListReq.getPageSize());
        }

        List<Product> productList = productMapper.selectList(productListQuery);
        PageInfo pageInfo = new PageInfo(productList);
        return pageInfo;

    }

    private void getListArry(List<CategoryVO> categoryVOS, ArrayList<Integer> integers){
        for (CategoryVO category : categoryVOS) {
            if (category != null) {
                integers.add(category.getId());
                getListArry(category.getCategoryList(), integers);
            }
        }
    }

}
