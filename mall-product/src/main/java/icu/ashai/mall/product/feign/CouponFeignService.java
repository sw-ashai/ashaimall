package icu.ashai.mall.product.feign;

import icu.ashai.common.to.SkuReductionTo;
import icu.ashai.common.to.SpuBoundsTo;
import icu.ashai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Ashai
 * @date 2/23/2022 8:07 PM
 * @Description 优惠远程调用
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

    /**
     * 保存spu优惠信息
     * @param spuBoundsTo 优惠信息
     * @return result
     */
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundsTo spuBoundsTo);

    /**
     * 保存Sku满减信息
     * @param skuReductionTo 满减信息
     * @return result
     */
    @PostMapping("/coupon/skufullreduction/saveInfo")
    R saveSkuReduction(SkuReductionTo skuReductionTo);
}
