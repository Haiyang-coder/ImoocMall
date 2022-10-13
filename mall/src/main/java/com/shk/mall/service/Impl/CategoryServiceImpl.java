package com.shk.mall.service.Impl;/**
 * @author: sunhengkang
 * @date:2022/10/13
 */

import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.dao.CategoryMapper;
import com.shk.mall.model.pojo.Category;
import com.shk.mall.model.request.AddCategoryRequest;
import com.shk.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@BelongsProject: mall
 *@BelongsPackage: com.shk.mall.service.Impl
 *@Author: sunhaiyang
 *@CreateTime: 2022-10-13  10:32
 *@Description: 添加商品类别的Service层
 *@Version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public void add(AddCategoryRequest addCategoryRequest) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryRequest, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryRequest.getName());
        if( categoryOld != null){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_NAME_EXIST);
        }
        int insert = categoryMapper.insertSelective(category);
        if (insert != 1){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_INSERT);
        }

    }
}
