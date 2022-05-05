package icu.ashai.mall.search.controller;

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

	@GetMapping("/list.html")
	public String listPage(){
		return "list";
	}
}
