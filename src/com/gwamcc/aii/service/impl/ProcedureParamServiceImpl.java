package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.ProcedureParamDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.ProcedureParamForm;
import com.gwamcc.aii.service.ProcedureParamService;

/**
 * 存储过程参数服务实现类
 * @author 张亚平
 *
 */
@Service
@CacheConfig(cacheNames="T_ObjParam")
public class ProcedureParamServiceImpl implements ProcedureParamService {
	@Autowired
    private ProcedureParamDao dao;

	@Override
	@Cacheable(key="#objID")
	public List<DefaultForm> getParamName(int objID) {
		return dao.getParamName(objID);
	}

	@Override
	@Cacheable(key="#objID")
	public List<DefaultForm> getParamList(int objID) {
		return dao.getParamList(objID);
	}

	@Override
	@Cacheable(key="#root.methodName",value={"T_ObjParam","T_Dict"})
	public List<DefaultForm> getParamType() {
		return dao.getParamType();
	}

	@Override
	@Cacheable(key="#proParam.toString()+#page.toString()")
	public PageDataForm getParamList(ProcedureParamForm proParam, PageForm page) {
		return dao.getParamList(proParam, page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(ProcedureParamForm proParam) {
		return dao.edit(proParam);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(ProcedureParamForm proParam) {
		return dao.append(proParam);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int paramID) {
		return dao.remove(paramID);
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
