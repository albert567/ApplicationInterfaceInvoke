package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.MethodParamDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MethodParamForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.ParamService;

/**
 * 接口方法服务实现类
 * @author 张亚平
 *
 */
@Service
@CacheConfig(cacheNames="T_InterfaceMethodParameter")
public class ParamServiceImpl implements ParamService {
	@Autowired
    private MethodParamDao dao;
	@Override
	@Cacheable(key="#methodID")
	public List<DefaultForm> getParamName(int methodID) {
		// TODO Auto-generated method stub
		return dao.getParamName(methodID);
	}
	@Override
	@Cacheable(key="#methodID")
	public List<DefaultForm> getParamList(int methodID) {
		// TODO Auto-generated method stub
		return dao.getParamList(methodID);
	}
	@Override
	@Cacheable(key="#root.methodName",value={"T_InterfaceMethodParameter","T_Dict"})
	public List<DefaultForm> getParamType() {
		// TODO Auto-generated method stub
		return dao.getParamType();
	}
	@Override
	@Cacheable(key="#methodParam.toString()+#page.toString()")
	public PageDataForm getParamList(DefaultForm methodParam, PageForm page) {
		// TODO Auto-generated method stub
		return dao.getParamList(methodParam, page);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(MethodParamForm methodParam) {
		// TODO Auto-generated method stub
		return dao.edit(methodParam);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(MethodParamForm methodParam) {
		// TODO Auto-generated method stub
		return dao.append(methodParam);
	}
	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int paramID) {
		// TODO Auto-generated method stub
		return dao.remove(paramID);
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
