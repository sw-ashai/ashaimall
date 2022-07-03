package icu.ashai.mall.search.service;

import icu.ashai.mall.search.vo.SearchParam;
import icu.ashai.mall.search.vo.SearchResult;

/**
 * @author ashai
 * @date 2022/5/5 22:03
 * @email ashai.cn@gmail.com
 * @description 搜索服务
 */
public interface MallSearchService {
	/**
	 * 搜索方法
	 *
	 * @param searchParam 搜索参数
	 * @return 搜索结果
	 */
	SearchResult search(SearchParam searchParam);
}
