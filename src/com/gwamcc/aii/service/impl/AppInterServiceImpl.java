package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppInterDao;
import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AppInterService;
/**
 * 应用接口服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_Interface")
public class AppInterServiceImpl implements AppInterService {
	@Autowired
    private AppInterDao dao;

	@Override
	@Cacheable(key="#form.toString()+#page.toString()")
	public PageDataForm getAppInterList(DefaultForm form, PageForm page) {
		return dao.getAppInterList(form, page);
	}


	@Override
	@Cacheable(key="#appId")
	public List<DefaultForm> getInterList(int appId) {
		return dao.getInterList(appId);
	}

	@Override
	@Cacheable(key="#root.methodName",value={"T_Interface","T_Dict"})
	public List<DefaultForm> getInterType() {
		return dao.getInterType();
	}


	@Override
	@Cacheable(key="#ApplicationID")
	public List<DefaultForm> getInterName(int ApplicationID) {
		return dao.getInterName(ApplicationID);
	}


	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(AppInterForm app) {
		return dao.edit(app);
	}


	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(AppInterForm app) {
		return dao.append(app);
	}


	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int interfaceID) {
		return dao.remove(interfaceID);
	}


	@Override
	@CacheEvict(allEntries=true)
	public Object upload(MultipartFile file) throws Exception{
		return dao.upload(file);
	}


	@Override
	public Object download() throws Exception{
		return dao.download();
	}


}
