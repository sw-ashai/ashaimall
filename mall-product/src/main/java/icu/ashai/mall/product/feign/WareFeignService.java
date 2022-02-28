package icu.ashai.mall.product.feign;

import icu.ashai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Ashai
 * @date 2/28/2022 8:05 PM
 * @Description
 */
@FeignClient("mall-ware")
public interface WareFeignService {
    /**
     * 获取sku 是否有库存
     *
     * @param skuIds sku列表
     * @return result
     */
    @PostMapping("/ware/waresku/hasstock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);
}
