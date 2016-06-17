package com.gwamcc.aii.web.controller.search;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SearchForm;
import com.gwamcc.aii.service.SearchService;

/**
 * 全文检索控制器
 * @author 张亚平
 *
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService service;

	/**
	 * 获取查询列表
	 * @param form
	 * @param page
	 * @return	查询列表
	 */
	@RequestMapping("/search/getList")
	@ResponseBody
    public PageDataForm getList(SearchForm form,PageForm page) {
         return service.getList(form, page);
    }
}
