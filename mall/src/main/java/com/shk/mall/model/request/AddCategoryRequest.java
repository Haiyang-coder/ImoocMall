package com.shk.mall.model.request;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: sunhengkang
 * @date:2022/10/13
 */
@Valid
public class AddCategoryRequest {
    @NotNull(message = "name不能空")
    @Size(min = 2, max = 5, message = "name在2和5之间")
    private  String name;
    @NotNull(message = "type不能空")
    @Max(value = 3,message = "type最大是3")
    private Integer type;
    @NotNull(message = "parentId不能空")
    private Integer parentId;
    @NotNull(message = "orderNum不能空")
    private Integer orderNum;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "addCategoryRequest{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", parentID=" + parentId +
                ", oderNumber=" + orderNum +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
