package com.shk.mall.controller;

import com.github.pagehelper.PageInfo;
import com.shk.mall.common.ApiRestRespose;
import com.shk.mall.common.MallCommon;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.pojo.User;
import com.shk.mall.model.request.AddCategoryRequest;
import com.shk.mall.model.request.UpdateCategoryReq;
import com.shk.mall.model.vo.CategoryVO;
import com.shk.mall.service.CategoryService;
import com.shk.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author: sunhengkang
 * @date:2022/10/13
 */
@Controller
public class CategoryController {
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

/*
 * @description: 添加新的商品分类
 * @author: yanhongwei
 * @date: 2022/10/13 10:52
 * @param: [session, addCategoryRequest]
 * @return:
 **/
    @ApiOperation("后台添加目录")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestRespose addCategoryReq(@Valid @RequestBody AddCategoryRequest addCategoryRequest){

        categoryService.add(addCategoryRequest);
        return ApiRestRespose.success();
    }

    @PostMapping("/admin/category/update")
    @ResponseBody
    @ApiOperation("后台更新目录")
    public ApiRestRespose updateCatgory(@Valid @RequestBody UpdateCategoryReq updateCategory){
        categoryService.update(updateCategory);
        return ApiRestRespose.success();
    }

    @PostMapping("/admin/category/delete")
    @ResponseBody
    @ApiOperation("删除品类")
    public  ApiRestRespose deleteCategory(@RequestParam("id") Integer id){
        categoryService.delete(id);
        return ApiRestRespose.success();
    }

    @PostMapping("/admin/category/list")
    @ResponseBody
    @ApiOperation("后台目录列表")
    public ApiRestRespose listCategoryForAdmin(@RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize) {
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestRespose.success(pageInfo);
    }

    @PostMapping("/category/list")
    @ResponseBody
    @ApiOperation("前台目录列表")
    public ApiRestRespose listCategoryForUser() {
        List<CategoryVO> categoryVOS = categoryService.listForUser(0);
        return ApiRestRespose.success(categoryVOS);
    }
}
