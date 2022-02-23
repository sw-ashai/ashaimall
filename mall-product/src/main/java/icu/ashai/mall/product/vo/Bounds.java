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
public class Bounds {

    /**
     * 金币积分
     */
    private BigDecimal buyBounds;
    /**
     * 成长积分
     */
    private BigDecimal growBounds;

}
