package com.shk.mall.service;

import com.github.pagehelper.PageInfo;
import com.shk.mall.model.request.AddCategoryRequest;
import com.shk.mall.model.request.UpdateCategoryReq;
import com.shk.mall.model.vo.CategoryVO;

import java.util.List;

/**
 * @
 * @author: sunhengkang
 * @date:2022/10/13
 */
public interface CategoryService {
    public void add(AddCategoryRequest addCategoryRequest);

    public void update(UpdateCategoryReq updateCategory);

    public void delete(Integer id);

    public PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    public List<CategoryVO> listForUser(Integer parentId);

}
