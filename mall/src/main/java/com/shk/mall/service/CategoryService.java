package com.shk.mall.service;

import com.shk.mall.model.request.AddCategoryRequest;
import com.shk.mall.model.request.UpdateCategoryReq;

/**
 * @
 * @author: sunhengkang
 * @date:2022/10/13
 */
public interface CategoryService {
    public void add(AddCategoryRequest addCategoryRequest);

    public void update(UpdateCategoryReq updateCategory);
}
