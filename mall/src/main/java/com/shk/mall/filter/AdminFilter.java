package com.shk.mall.filter;/**
 * @author: sunhengkang
 * @date:2022/10/13
 */


import com.shk.mall.common.MallCommon;
import com.shk.mall.model.pojo.User;
import com.shk.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *@BelongsProject: mall
 *@BelongsPackage: com.shk.mall.filter
 *@Author: sunhaiyang
 *@CreateTime: 2022-10-13  16:06
 *@Description: 过滤请求,实现管理员权限的验证管理
 *@Version: 1.0
 */
public class AdminFilter implements Filter {
    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest htpRequest =  (HttpServletRequest)request;
        HttpSession session = htpRequest.getSession();
        User user = (User)session.getAttribute(MallCommon.IMOOC_MALL_USER);
        if (user == null) {
            PrintWriter out = new HttpServletResponseWrapper(
                    (HttpServletResponse) response).getWriter();
            out.write("{\n"
                    + "    \"status\": 10007,\n"
                    + "    \"msg\": \"NEED_LOGIN\",\n"
                    + "    \"data\": null\n"
                    + "}");
            out.flush();
            out.close();
            return;
        }
        if (!userService.checkIfAdmin(user)) {
            PrintWriter out = new HttpServletResponseWrapper(
                    (HttpServletResponse) response).getWriter();
            out.write("{\n"
                    + "    \"status\": 10009,\n"
                    + "    \"msg\": \"NEED_ADMIN\",\n"
                    + "    \"data\": null\n"
                    + "}");
            out.flush();
            out.close();
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
