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
public class Skus {
    /**
     * 属性
     */
    private List<Attr> attr;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 商品标题
     */
    private String skuTitle;
    /**
     * 商品副标题
     */
    private String skuSubtitle;
    /**
     * 商品图片
     */
    private List<Images> images;
    /**
     * 商品描述
     */
    private List<String> descar;
    /**
     * 满减折扣
     */
    private int fullCount;
    /**
     * 折扣力度
     */
    private BigDecimal discount;
    /**
     * 是否叠加优惠
     */
    private int countStatus;
    /**
     * 满减标准
     */
    private BigDecimal fullPrice;
    /**
     * 满减价格
     */
    private BigDecimal reducePrice;
    /**
     * 价格状态
     */
    private int priceStatus;
    /**
     * 会员价格
     */
    private List<MemberPrice> memberPrice;

}
