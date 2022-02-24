package icu.ashai.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ashai
 * @date 2/23/2022 8:12 PM
 * @Description
 */
@Data
public class SpuBoundsTo {
    /**
     * spuId
     */
    private Long spuId;

    /**
     * 金币积分
     */
    private BigDecimal buyBounds;
    /**
     * 成长积分
     */
    private BigDecimal growBounds;

}
