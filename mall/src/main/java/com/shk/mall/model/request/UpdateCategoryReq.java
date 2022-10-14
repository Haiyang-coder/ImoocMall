package com.shk.mall.model.request;/**
 * @author: sunhengkang
 * @date:2022/10/13
 */

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *@BelongsProject: mall
 *@BelongsPackage: com.shk.mall.model.request
 *@Author: sunhaiyang
 *@CreateTime: 2022-10-13  15:33
 *@Description: TODO
 *@Version: 1.0
 */
@Valid
public class UpdateCategoryReq {

    @NotNull(message = "id不能空")
    private Integer id;


    @Override
    public String toString() {
        return "updateCategoryReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                '}';
    }

    @Size(min = 2, max = 5, message = "name在2和5之间")
    private  String name;
    @Max(value = 3,message = "type最大是3")
    private Integer type;
    private Integer parentId;
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
