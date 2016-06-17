package com.gwamcc.aii.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.IMDORelationDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.IMDORelationForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.IMDORelationService;
/**
 * 接口方法与数据库字段关系服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_IM_DBOC_Rel")
public class IMDORelationServiceImpl implements IMDORelationService {
	@Autowired
    private IMDORelationDao dao;

	@Override
	@Cacheable(key="#objID")
	public List<DefaultForm> getMethodByObjID(int objID) {
		// TODO Auto-generated method stub
		return dao.getMethodByObjID(objID);
	}

	@Override
	@Cacheable(key="#methodID")
	public List<DefaultForm> getObjByMeID(int methodID) {
		// TODO Auto-generated method stub
		return dao.getObjByMeID(methodID);
	}

	@Override
	@Cacheable(key="#form.toString()+#page.toString()")
	public PageDataForm getList(IMDORelationForm form, PageForm page) throws Exception {
		return dao.getList(form,page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object edit(IMDORelationForm app) throws Exception {
		return dao.edit(app);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object append(IMDORelationForm app) throws Exception {
		return dao.append(app);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object remove(IMDORelationForm bean) throws Exception {
		return dao.remove(bean);
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
