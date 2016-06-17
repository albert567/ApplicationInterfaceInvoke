package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.ApplicationDao;
import com.gwamcc.aii.forms.ApplicationForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.ApplicationService;
/**
 * 应用程序服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_Application")
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
    private ApplicationDao dao;
	@Override
	@Cacheable(key="#root.methodName")
	public List<DefaultForm> getApp() {
		return dao.getApp();
	}
	@Override
	@Cacheable(key="#app.toString()+#page.toString()")
	public Object getAppList(ApplicationForm app, PageForm page) {
		System.out.println(app.toString()+page.toString());
		return dao.getAppList(app,page);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(ApplicationForm app) {
		return dao.append(app);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(ApplicationForm app) {
		return dao.edit(app);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int applicationID) {
		return dao.remove(applicationID);
	}
	@Override
	@Cacheable(key="#root.methodName",value={"T_Application","T_Dict"})
	public Object appType() {
		return dao.appType();
	}
	@Override
	@CacheEvict(allEntries=true)
	public Object upload(MultipartFile file)throws Exception{
		return dao.upload(file);
	}
	@Override
	public Object download()throws Exception{
		return dao.download();
	}
	@Override
	@Cacheable(key="#appID")
	public List<DefaultForm> getForceData(int appID) {
		// TODO Auto-generated method stub
		return dao.getForceData(appID);
	}

}
