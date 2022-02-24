package icu.ashai.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.to.SkuReductionTo;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:26:36
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存sku满减信息
     * @param skuReductionTo 满减信息
     */
    void saveSkuReduction(SkuReductionTo skuReductionTo);
}

