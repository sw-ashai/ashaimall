package icu.ashai.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 5:25 PM 2/23/2022
 */
@Data
public class MemberPrice {

    /**
     * 会员id
     */
    private Long id;
    /**
     * 会员名
     */
    private String name;
    /**
     * 会员价格
     */
    private BigDecimal price;

}
