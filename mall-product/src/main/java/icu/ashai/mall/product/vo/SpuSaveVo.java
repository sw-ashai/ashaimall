package icu.ashai.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 5:25 PM 2/23/2022
 */
@Data
public class SpuSaveVo {
    /**
     * spu名称
     */
    private String spuName;
    /**
     * spu描述
     */
    private String spuDescription;
    /**
     * 分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 发布状态
     */
    private int publishStatus;
    /**
     * 描述信息
     */
    private List<String> decript;
    /**
     * 图片
     */
    private List<String> images;
    /**
     * 积分
     */
    private Bounds bounds;
    /**
     * 基本属性
     */
    private List<BaseAttrs> baseAttrs;
    /**
     * spu下的sku
     */
    private List<Skus> skus;


}
