package icu.ashai.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.ware.entity.WareSkuEntity;
import icu.ashai.mall.ware.vo.SkuHasStockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:57:27
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加库存
     *
     * @param skuId  sku id
     * @param wareId 仓库id
     * @param skuNum sku数量
     */
    void addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 获取sku id 是否有库存
     * @param skuIds sku id
     * @return result
     */
    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
}

