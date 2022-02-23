package icu.ashai.mall.product.vo;

import lombok.Data;

/**
 * @Description
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 5:25 PM 2/23/2022
 */
@Data
public class BaseAttrs {

    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性值
     */
    private String attrValues;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private int showDesc;

}
