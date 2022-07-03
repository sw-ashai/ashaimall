package icu.ashai.mall.search.vo;

import icu.ashai.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ashai
 * @date 2022/5/6 22:27
 * @email ashai.cn@gmail.com
 * @description
 */
@Data
public class SearchResult {
    /**
     * 查询到的商品信息
     */
    private List<SkuEsModel> products;
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 总记录数
     */
    private Long Total;
    /**
     * 总页码
     */
    private Integer totalPages;
    /***
     * 查询到的结果涉及的品牌
     */
    private List<BrandVo> brands;

    /**
     * 查询到的结果涉及的属性
     */
    private List<AttrVo> attrs;

    /**
     * 查询到的结果设置的分类
     */
    private List<CatalogVo> catalogs;

    private List<Integer> pageNavs;

    /**
     * 面包屑导航数据
     */
    private List<SearchResult.NavVo> navs = new ArrayList<>();
    private List<Long> attrIds = new ArrayList<>();

    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }

    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;
    }

    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }

}
