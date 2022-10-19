package com.shk.mall.model.request;/**
 * @author: sunhengkang
 * @date:2022/10/17
 */

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *@BelongsProject: mall
 *@BelongsPackage: com.shk.mall.model.request
 *@Author: sunhaiyang
 *@CreateTime: 2022-10-17  15:45
 *@Description: TODO
 *@Version: 1.0
 */
public class UpdateProductReq {
    @NotNull(message = "id不能为空")
    private Integer  id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull(message = "名字不能为空")
    private String name;

    @NotNull(message = "品类不能为空")
    private Integer categoryId;



    @NotNull(message = "价格不能为空")
    @Min(value = 0,message = "价格最低0元")
    private Integer price;

    @NotNull(message = "stock不能空")
    @Max(value = 10000,message = "库存在0和10000之间")
    private Integer stock;

    private Integer status;
    private String detail;

    @NotNull(message = "图片不能为空")
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
