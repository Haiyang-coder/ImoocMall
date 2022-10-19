package com.shk.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author: sunhengkang
 * @date:2022/10/12
 * 全局的使用的公共的宏定义
 */
@Component
public class MallCommon {
    //盐值
    public final static String SAULT = "*&^$#JHYVBI>><";
    //session标签
    public static final String IMOOC_MALL_USER = "imooc_mall_user";

    public static String FILE_SRC;

    @Value("${file_src}")
    public void setFileSrc(String fileSrc){
        FILE_SRC  = fileSrc;
    }

    public interface ProductListOrderBy {

        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc", "price asc");
    }


}
