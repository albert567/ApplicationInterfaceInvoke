package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.MethodDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.MethodService;

/**
 * 接口方法服务实现类
 * @author 张亚平
 *
 */
@Service
@CacheConfig(cacheNames="T_InterfaceMethod")
public class MethodServiceImpl implements MethodService {
	@Autowired
    private MethodDao dao;
	@Override
	@Cacheable(key="#interfaceID")
	public List<DefaultForm> getMethodName(int interfaceID) {
		return dao.getMethodName(interfaceID);
	}
	@Override
	@Cacheable(key="#interfaceID")
	public List<DefaultForm> getMethodList(int interfaceID) {
		// TODO Auto-generated method stub
		return dao.getMethodList(interfaceID);
	}

	@Override
	@Cacheable(key="#root.methodName",value={"T_InterfaceMethod","T_Dict"})
	public List<DefaultForm>getMethodType(){
		return dao.getMethodType();
	}

	@Override
	@Cacheable(key="#interMethod.toString()+#page.toString()")
	public PageDataForm getMethodList(DefaultForm interMethod,PageForm page){
		return dao.getMethodList(interMethod, page);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(InterMethodForm interMethod) {
		// TODO Auto-generated method stub
		return dao.edit(interMethod);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(InterMethodForm interMethod) {
		// TODO Auto-generated method stub
		return dao.append(interMethod);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int methodID) {
		// TODO Auto-generated method stub
		return dao.remove(methodID);
	}
	@Override
	@CacheEvict(allEntries=true)
	public Object upload(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		return dao.upload(file);
	}
	@Override
	public Object download() throws Exception {
		// TODO Auto-generated method stub
		return dao.download();
	}

}
