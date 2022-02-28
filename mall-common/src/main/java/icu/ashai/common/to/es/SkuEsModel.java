package icu.ashai.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ashai
 * @date 2/28/2022 6:22 PM
 * @Description sku es 模型
 */
@Data
public class SkuEsModel {
    /**
     * sku id
     */
    private Long skuId;
    /**
     * spu id
     */
    private Long spuId;
    /**
     * 商品标题
     */
    private String skuTitle;
    /**
     * 商品价格
     */
    private BigDecimal skuPrice;
    /**
     * 商品图片
     */
    private String skuImg;
    /**
     * 销量
     */
    private Long saleCount;
    /**
     * 是否有库存
     */
    private Boolean hasStock;
    /**
     * 热度分数
     */
    private Long hotScore;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 分类id
     */
    private Long catalogId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品牌图片
     */
    private String brandImg;
    /**
     * 分类名称
     */
    private String catalogName;
    /**
     * 商品属性
     */
    private List<Attrs> attrs;

    @Data
    public static class Attrs {
        /**
         * 属性id
         */
        private Long attrId;
        /**
         * 属性名称
         */
        private String attrName;
        /**
         * 属性值
         */
        private String attrValue;
    }
}
