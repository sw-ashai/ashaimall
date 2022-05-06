package icu.ashai.mall.search.controller;

import icu.ashai.mall.search.service.MallSearchService;
import icu.ashai.mall.search.vo.SearchParam;
import icu.ashai.mall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ashai
 * @date 2022/5/4 21:43
 * @email ashai.cn@gmail.com
 * @description
 */
@Controller
public class SearchController {

	/**
	 * 搜索服务
	 */
	MallSearchService mallSearchService;

	@Autowired
	public SearchController(MallSearchService mallSearchService) {
		this.mallSearchService = mallSearchService;
	}

	@GetMapping("/list.html")
	public String listPage(SearchParam searchParam){
		 SearchResult result = mallSearchService.search(searchParam);
		return "list";
	}
}
