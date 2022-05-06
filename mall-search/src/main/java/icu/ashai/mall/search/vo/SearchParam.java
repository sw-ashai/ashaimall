package icu.ashai.mall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @author shiwei
 * @date 2022/5/5 22:01
 * @email shiwei@rmitec.cn
 * @description 搜索条件
 */
@Data
public class SearchParam {
	/**
	 * 页面传入全文匹配关键字
	 */
	private String keyword;
	/**
	 * 三级分类id
	 */
	private Long catalog3Id;
	/**
	 * 排序
	 */
	private String sort;
	/**
	 * 仅显示有货
	 */
	private Integer hasStock;
	/**
	 * 商品价格
	 */
	private String skuPrice;
	/**
	 * 品牌id
	 */
	private List<Long> brandId;
	/**
	 * 属性
	 */
	private List<String> attrs;
	/**
	 * 页码
	 */
	private Integer pageNum;


}
