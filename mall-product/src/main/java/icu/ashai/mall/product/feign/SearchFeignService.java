package icu.ashai.mall.product.feign;

import icu.ashai.common.to.es.SkuEsModel;
import icu.ashai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Ashai
 * @date 2/28/2022 8:55 PM
 * @Description
 */
@FeignClient("mall-search")
public interface SearchFeignService {
    /**
     * 上架上皮内
     *
     * @param skuEsModels sku 上架模型
     * @return result
     */
    @PostMapping("/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
