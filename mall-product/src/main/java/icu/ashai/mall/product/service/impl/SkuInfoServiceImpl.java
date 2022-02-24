package icu.ashai.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.product.dao.SkuInfoDao;
import icu.ashai.mall.product.entity.SkuInfoEntity;
import icu.ashai.mall.product.service.SkuInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description sku service
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 3:24 PM 2/24/2022
 */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String catelogId = (String) params.get("catelogId");
        String brandId = (String) params.get("brandId");
        String min = (String) params.get("min");
        String max = (String) params.get("max");

        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new LambdaUpdateWrapper<SkuInfoEntity>()
                        .and(StringUtils.isNotBlank(key), wrapper -> wrapper.eq(SkuInfoEntity::getSkuId, key).or().eq(SkuInfoEntity::getSkuName, key))
                        .eq(StringUtils.isNotBlank(catelogId) && !"0".equalsIgnoreCase(catelogId), SkuInfoEntity::getCatalogId, catelogId)
                        .eq(StringUtils.isNotBlank(brandId) && !"0".equalsIgnoreCase(brandId), SkuInfoEntity::getBrandId, brandId)
                        .ge(StringUtils.isNotBlank(min) && !"0".equalsIgnoreCase(min), SkuInfoEntity::getPrice, min)
                        .le(StringUtils.isNotBlank(max) && !"0".equalsIgnoreCase(max), SkuInfoEntity::getPrice, max)
        );

        return new PageUtils(page);
    }

}