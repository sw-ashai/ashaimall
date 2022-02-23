package icu.ashai.mall.product.vo;

import lombok.Data;


/**
 * @Description
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 5:25 PM 2/23/2022
 */
@Data
public class Attr {

    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 属性值
     */
    private String attrValue;
}
