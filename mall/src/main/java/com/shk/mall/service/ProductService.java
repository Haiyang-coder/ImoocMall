package com.shk.mall.service;

import com.github.pagehelper.PageInfo;
import com.shk.mall.model.pojo.Product;
import com.shk.mall.model.request.AddProductRequst;
import com.shk.mall.model.request.ProductListReq;

/**
 * @author: sunhengkang
 * @date:2022/10/15
 */
public interface ProductService {
    public void addProduct(AddProductRequst addProductRequst);

    public void updateProduct(Product product);

    public void deleteProduct(Integer id);

    public void batchUpdateSellStatus(Integer[] ids,Integer sellStatus);
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    public Product detail(Integer id);

    public PageInfo list(ProductListReq productListReq);
}
