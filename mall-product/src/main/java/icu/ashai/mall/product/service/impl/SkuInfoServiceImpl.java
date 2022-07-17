package icu.ashai.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.product.dao.SkuInfoDao;
import icu.ashai.mall.product.entity.SkuImagesEntity;
import icu.ashai.mall.product.entity.SkuInfoEntity;
import icu.ashai.mall.product.entity.SpuInfoDescEntity;
import icu.ashai.mall.product.service.*;
import icu.ashai.mall.product.vo.SkuItemVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description sku service
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 3:24 PM 2/24/2022
 */
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    /**
     * 图片服务类
     */
    private final SkuImagesService imagesService;

    /**
     * spu 服务类
     */
    private final SpuInfoDescService spuInfoDescService;

    /**
     * 属性分组服务类
     */
    private final AttrGroupService attrGroupService;

    /**
     * 销售属性服务类
     */
    private final SkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 线程池
     */
    private final ThreadPoolExecutor executor;

    @Autowired
    public SkuInfoServiceImpl(SkuImagesService imagesService, SpuInfoDescService spuInfoDescService,
                              AttrGroupService attrGroupService, SkuSaleAttrValueService skuSaleAttrValueService,
                              ThreadPoolExecutor executor) {
        this.imagesService = imagesService;
        this.spuInfoDescService = spuInfoDescService;
        this.attrGroupService = attrGroupService;
        this.skuSaleAttrValueService = skuSaleAttrValueService;
        this.executor = executor;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String catelogId = (String) params.get("catelogId");
        String brandId = (String) params.get("brandId");
        String min = (String) params.get("min");
        String max = (String) params.get("max");

        IPage<SkuInfoEntity> page = this.page(new Query<SkuInfoEntity>().getPage(params),
                                              new LambdaUpdateWrapper<SkuInfoEntity>().and(StringUtils.isNotBlank(key),
                                                                                           wrapper -> wrapper.eq(
                                                                                                                     SkuInfoEntity::getSkuId,
                                                                                                                     key)
                                                                                                             .or()
                                                                                                             .eq(SkuInfoEntity::getSkuName,
                                                                                                                 key))
                                                                                      .eq(StringUtils.isNotBlank(
                                                                                                  catelogId) && !"0".equalsIgnoreCase(
                                                                                                  catelogId),
                                                                                          SkuInfoEntity::getCatalogId,
                                                                                          catelogId)
                                                                                      .eq(StringUtils.isNotBlank(
                                                                                                  brandId) && !"0".equalsIgnoreCase(
                                                                                                  brandId),
                                                                                          SkuInfoEntity::getBrandId,
                                                                                          brandId)
                                                                                      .ge(StringUtils.isNotBlank(
                                                                                                  min) && !"0".equalsIgnoreCase(
                                                                                                  min),
                                                                                          SkuInfoEntity::getPrice, min)
                                                                                      .le(StringUtils.isNotBlank(
                                                                                                  max) && !"0".equalsIgnoreCase(
                                                                                                  max),
                                                                                          SkuInfoEntity::getPrice,
                                                                                          max));

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        return this.list(new LambdaQueryWrapper<SkuInfoEntity>().eq(SkuInfoEntity::getSpuId, spuId));
    }

    @Override
    public SkuItemVo item(String skuId) throws ExecutionException, InterruptedException {
        SkuItemVo itemVo = new SkuItemVo();
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            // 1.sku基本信息获取
            SkuInfoEntity info = getById(skuId);
            itemVo.setInfo(info);
            return info;
        }, executor);

        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            // 3.获取spu的销售属性组合
            List<SkuItemVo.SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrsBySpuId(res.getSpuId());
            itemVo.setSaleAttr(saleAttrVos);
        }, executor);

        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((res) -> {
            // 4.获取spu的介绍
            SpuInfoDescEntity spuInfo = spuInfoDescService.getById(res.getSpuId());
            itemVo.setDesc(spuInfo);
        }, executor);
        CompletableFuture<Void> baseAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            // 5.获取spu的规格参数信息
            List<SkuItemVo.SpuItemAttrGroupVo> attrGroups = attrGroupService.getAttrGroupWithAttrsBySpuId(
                    res.getSpuId(), res.getCatalogId());
            itemVo.setGroupAttrs(attrGroups);
        }, executor);
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            // 2.sku图片信息
            List<SkuImagesEntity> images = imagesService.getImagesBySkuId(skuId);
            itemVo.setImages(images);
        }, executor);
        // 等待所有任务完成
        CompletableFuture.allOf(saleAttrFuture, descFuture, baseAttrFuture, imageFuture).get();

        return itemVo;
    }

}