package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppCodeDao;
import com.gwamcc.aii.forms.AppCodeForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.service.AppCodeService;
/**
 * 应用系统代码结构服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_CodeStructure")
public class AppCodeServiceImpl implements AppCodeService {

	@Autowired
	private AppCodeDao dao;
	@Override
	@CacheEvict(key="#appID")
	public List<DefaultForm> getRoot(int appID){
		return dao.getRoot(appID);
	}
	@Override
	@CacheEvict(key="#codeID")
	public List<DefaultForm> getChild(int codeID){
		return dao.getChild(codeID);
	}

	@Override
	@CacheEvict(key="#appID")
	public List<DefaultForm> getCode(int appID){
		return dao.getCode(appID);
	}

	@Override
	@CacheEvict(key="#form.toString()")
	public Object getList(AppCodeForm form) throws Exception{
		return dao.getList(form);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object edit(AppCodeForm form) throws Exception {
		return dao.edit(form);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object append(AppCodeForm form) throws Exception {
		return dao.append(form);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object remove(AppCodeForm form) throws Exception {
		return dao.remove(form);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object upload(MultipartFile file,int appID) throws Exception {
		return dao.upload(file,appID);
	}

	@Override
	public Object download() throws Exception {
		return dao.download();
	}

}
