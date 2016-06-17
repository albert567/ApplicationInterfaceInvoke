package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppFuncDao;
import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AppFuncService;
/**
 * 应用功能服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_ApplicationFunction")
public class AppFuncServiceImpl implements AppFuncService {
	@Autowired
    private AppFuncDao dao;

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(AppFuncForm app)  throws Exception {
		return dao.edit(app);
	}


	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(AppFuncForm app) throws Exception {
		return dao.append(app);
	}


	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(AppFuncForm form)  throws Exception {
		return dao.remove(form);
	}


	@Override
	@Cacheable(key="#form.toString()+#page.toString()")
	public PageDataForm getList(AppFuncForm form, PageForm page) throws Exception {
		return dao.getList(form,page);
	}


	@Override
	@Cacheable(key="#applicationID")
	public List<DefaultForm> getFuncList(int applicationID) throws Exception {
		return dao.getFuncList(applicationID);
	}


	@Override
	@Cacheable(key="#applicationID")
	public List<DefaultForm> getName(int applicationID) throws Exception {
		return dao.getName(applicationID);
	}


	@Override
	@CacheEvict(allEntries=true)
	public Object upload(MultipartFile file) throws Exception {
		return dao.upload(file);
	}


	@Override
	public Object download() throws Exception {
		return dao.download();
	}

}
