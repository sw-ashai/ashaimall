package icu.ashai.mall.search.feign;

import icu.ashai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("mall-product")
public interface ProductFeignService {

    /**
     * 属性信息
     */
    @GetMapping("product/attr/info/{attrId}")
    R getAttrInfo(@PathVariable("attrId") Long attrId);

    /**
     * 品牌信息
     */
    @PostMapping("product/brand/infos")
    R getBrandsInfo(List<Long> brandIds);

    /**
     * 信息
     */
    @GetMapping("product/category/info/{catId}")
    R getCategoryInfo(@PathVariable("catId") Long catId);
}
