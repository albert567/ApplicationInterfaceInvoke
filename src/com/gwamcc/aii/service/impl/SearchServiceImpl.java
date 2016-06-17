package com.gwamcc.aii.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.SearchDao;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SearchForm;
import com.gwamcc.aii.service.SearchService;
/**
 * 全文检索服务实现类
 * @author 张亚平
 *
 */
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
    private SearchDao dao;

	@Override
	public PageDataForm getList(SearchForm form, PageForm page) {
		// TODO Auto-generated method stub
		return dao.getList(form, page);
	}

}
