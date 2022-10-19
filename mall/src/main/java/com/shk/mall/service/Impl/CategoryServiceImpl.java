package com.shk.mall.service.Impl;/**
 * @author: sunhengkang
 * @date:2022/10/13
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shk.mall.exception.ImoocMallException;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.dao.CategoryMapper;
import com.shk.mall.model.pojo.Category;
import com.shk.mall.model.request.AddCategoryRequest;
import com.shk.mall.model.request.UpdateCategoryReq;
import com.shk.mall.model.vo.CategoryVO;
import com.shk.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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


    /*
     * @description:添加新的目录
     * @author: yanhongwei
     * @date: 2022/10/13 15:56
     * @param: [addCategoryRequest]
     * @return: void
     **/
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
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_UPDATE);
        }

    }

    /*
     * @description:更新category的数据库
     * @author: yanhongwei
     * @date: 2022/10/13 15:56
     * @param: [updateCategory]
     * @return: void
     **/
    @Override
    public void update(UpdateCategoryReq updateCategory) {
        Category category = new Category();
        BeanUtils.copyProperties(updateCategory, category);
        Category categoryOld = categoryMapper.selectByName(updateCategory.getName());
        if (updateCategory.getName() != null) {
            if (categoryOld != null && !categoryOld.getId().equals(updateCategory.getId())){
                throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_NAME_EXIST);
            }
        }
        UpdateCategory(category);
    }

    @Override
    public void delete(Integer id) {
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
        if (categoryOld == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_DELETE);
        }
        int iCount = categoryMapper.deleteByPrimaryKey(id);
        if (iCount != 1){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_DELETE);
        }

    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "type, order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo pageInfo = new PageInfo(categoryList);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "listCategoryForCustomer")
    public List<CategoryVO> listForUser(Integer parentId) {
        ArrayList<CategoryVO> categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList, parentId);
        return categoryVOList;
    }

    private void recursivelyFindCategories(List<CategoryVO> categoryVOList, Integer parentId) {
        //递归获取所有子类别，并组合成为一个“目录树”
        List<Category> categoryList = categoryMapper.selectCategoriesByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVO categoryVO = new CategoryVO();
                BeanUtils.copyProperties(category, categoryVO);
                categoryVOList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getCategoryList(), categoryVO.getId());
            }
        }
    }


    private void UpdateCategory(Category category){
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count != 1){
            throw new ImoocMallException(ImoocMallExceptionEnum.ERROR_SQL_UPDATE);
        }
    }
}
