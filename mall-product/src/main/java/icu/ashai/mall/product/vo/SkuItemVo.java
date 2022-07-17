package icu.ashai.mall.product.vo;

import icu.ashai.mall.product.entity.SkuImagesEntity;
import icu.ashai.mall.product.entity.SkuInfoEntity;
import icu.ashai.mall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

/**
 * @author ashai
 * @date 2022/7/17 17:12
 * @email ashai@gmail.com
 * @description
 */
@Data
public class SkuItemVo {
    /**
     * sku基本信息
     */
    private SkuInfoEntity info;

    private boolean hasStock = true;
    /**
     * sku的图片信息
     */
    private List<SkuImagesEntity> images;
    /**
     * 获取spu的销售属性组合
     */
    private List<SkuItemSaleAttrVo> saleAttr;
    /**
     * 获取spu的介绍
     */
    private SpuInfoDescEntity desc;
    /**
     * 获取spu的规格参数信息
     */
    private List<SpuItemAttrGroupVo> groupAttrs;

    @Data
    public static class SkuItemSaleAttrVo{

        private Long attrId;
        private String attrName;
        private List<AttrValueWithSkuIdVo> attrValues;
    }

    @Data
    public static class SpuItemAttrGroupVo{
        private String groupName;
        private List<SpuBaseAttrVo> attrs;
    }

    @Data
    public static class SpuBaseAttrVo{
        private String attrName;
        private String attrValue;
    }

    @Data
    public static class AttrValueWithSkuIdVo{
        private String attrValue;
        private String skuIds;
    }
}
