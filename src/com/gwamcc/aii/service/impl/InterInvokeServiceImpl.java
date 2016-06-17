package com.gwamcc.aii.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.InterInvokeDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterInvokeForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.InterInvokeService;

/**
 * 应用接口调用服务实现类
 * @author 张亚平
 *
 */
@Service
@CacheConfig(cacheNames="T_ApplicationInterfaceInvoke")
public class InterInvokeServiceImpl implements InterInvokeService {
	@Autowired
    private InterInvokeDao dao;

	@Override
	@Cacheable(key="#interInvoke.toString()+#page.toString()")
	public PageDataForm getInterInvokeList(DefaultForm interInvoke,PageForm page){
		return dao.getInterInvokeList(interInvoke, page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(InterInvokeForm interInvoke) {
		// TODO Auto-generated method stub
		return dao.edit(interInvoke);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(InterInvokeForm interInvoke) {
		// TODO Auto-generated method stub
		return dao.append(interInvoke);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int interInvokeID) {
		// TODO Auto-generated method stub
		return dao.remove(interInvokeID);
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
