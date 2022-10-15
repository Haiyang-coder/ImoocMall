package com.shk.mall.model.request;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Valid
public class AddProductRequst {
    @NotNull(message = "名字不能为空")
    private String name;

    @NotNull(message = "图片不能为空")
    private String image;

    private String detail;

    @NotNull(message = "品类不能为空")
    private Integer categoryId;

    @NotNull(message = "价格不能为空")
    @Min(value = 0,message = "价格最低0元")
    private Integer price;

    @Size(min = 0, max = 10000,message = "库存在0和10000之间")
    private Integer stock;

    private Integer status;


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