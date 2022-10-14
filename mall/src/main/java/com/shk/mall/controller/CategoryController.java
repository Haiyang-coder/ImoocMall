package com.shk.mall.controller;

import com.shk.mall.common.ApiRestRespose;
import com.shk.mall.common.MallCommon;
import com.shk.mall.exception.ImoocMallExceptionEnum;
import com.shk.mall.model.pojo.User;
import com.shk.mall.model.request.AddCategoryRequest;
import com.shk.mall.model.request.UpdateCategoryReq;
import com.shk.mall.service.CategoryService;
import com.shk.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public ApiRestRespose addCategoryReq(HttpSession session,
                                         @Valid @RequestBody AddCategoryRequest addCategoryRequest){
        User user = (User)session.getAttribute(MallCommon.IMOOC_MALL_USER);
        if (user == null) {
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_NOT_LOGIN);
        }
        if (!userService.checkIfAdmin(user)) {
            return ApiRestRespose.error(ImoocMallExceptionEnum.ERROR_ROLE);
        }
        categoryService.add(addCategoryRequest);
        return ApiRestRespose.success();
    }

    @PostMapping("/admin/category/update")
    @ResponseBody
    @ApiOperation("后台更新目录")
    public ApiRestRespose updateCatgory(HttpSession session,
                                        @Valid @RequestBody UpdateCategoryReq updateCategory){
        categoryService.update(updateCategory);
        return ApiRestRespose.success();
    }
}
