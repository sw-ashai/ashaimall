package icu.ashai.mall.ware.feign;

import icu.ashai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ashai
 * @date 2/25/2022 5:16 PM
 * @Description
 */
@FeignClient("mall-product")
public interface ProductFeignService {

    /**
     * 查询sku详细信息
     *
     * @param skuId sku id
     * @return result
     */
    @RequestMapping("/product/skinful/info/{skuId}")
    R info(@PathVariable("skuId") Long skuId);
}
