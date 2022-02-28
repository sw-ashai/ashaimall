package icu.ashai.mall.search.service;

import icu.ashai.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author Ashai
 * @date 2/28/2022 8:33 PM
 * @Description
 */
public interface ProductSaveService {

    /**
     * 保存商品上架数据
     *
     * @param skuEsModels 上架数据
     * @return result
     * @throws IOException es调用错误
     */
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
