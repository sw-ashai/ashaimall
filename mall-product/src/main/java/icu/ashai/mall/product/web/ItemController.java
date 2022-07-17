package icu.ashai.mall.product.web;

import icu.ashai.mall.product.service.SkuInfoService;
import icu.ashai.mall.product.vo.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ExecutionException;

/**
 * @author ashai
 * @date 2022/7/17 16:55
 * @email ashai@gmail.com
 * @description
 */
@Controller
public class ItemController {


    /**
     * sku service
     */
    private final SkuInfoService skuInfoService;

    @Autowired
    public ItemController(SkuInfoService skuInfoService) {
        this.skuInfoService = skuInfoService;
    }

    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable String skuId, Model model) throws ExecutionException, InterruptedException {
        SkuItemVo itemVo = skuInfoService.item(skuId);
        model.addAttribute("item",itemVo);
        return "item";
    }
}
