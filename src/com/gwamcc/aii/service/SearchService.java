package com.gwamcc.aii.service;

import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SearchForm;

/**
 * 全文检索Action
 * @author 张亚平
 *
 */
public interface SearchService {
	/**
	 * 分页查询全文检索
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	PageDataForm getList(SearchForm form, PageForm page);
}
