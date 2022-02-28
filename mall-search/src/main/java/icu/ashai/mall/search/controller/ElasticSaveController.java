package icu.ashai.mall.search.controller;

import icu.ashai.common.exception.BizCodeEnum;
import icu.ashai.common.to.es.SkuEsModel;
import icu.ashai.common.utils.R;
import icu.ashai.mall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author Ashai
 * @date 2/28/2022 8:27 PM
 * @Description
 */
@RequestMapping("/save")
@RestController
@Slf4j
public class ElasticSaveController {

    /**
     * 商品保存service
     */
    private final ProductSaveService productSaveService;

    @Autowired
    public ElasticSaveController(ProductSaveService productSaveService) {
        this.productSaveService = productSaveService;
    }


    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        boolean b;
        try {
            b = productSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            log.error("ElasticSaveController商品上架错误: {}", e);
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION);
        }

        if (b){
            return R.ok();
        }else {
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION);
        }
    }
}
