package icu.ashai.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ashai
 * @date 2/23/2022 8:26 PM
 * @Description
 */
@Data
public class SkuReductionTo {

    /**
     * sku id
     */
    private Long skuId;
    /**
     * 满减折扣
     */
    private int fullCount;
    /**
     * 折扣力度
     */
    private BigDecimal discount;
    /**
     * 是否叠加优惠
     */
    private int countStatus;
    /**
     * 满减标准
     */
    private BigDecimal fullPrice;
    /**
     * 满减价格
     */
    private BigDecimal reducePrice;
    /**
     * 价格状态
     */
    private int priceStatus;
    /**
     * 会员价格
     */
    private List<MemberPrice> memberPrice;
}
