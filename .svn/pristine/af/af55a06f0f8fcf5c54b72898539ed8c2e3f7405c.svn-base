package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DictDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.service.DictService;
/**
 * 数据字典服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_Dict")
public class DictServiceImpl implements DictService {

	@Autowired
	private DictDao dao;

	@Override
	@Cacheable(key="#dType")
	public List<DefaultForm> validList(String dType) throws Exception{
		return dao.validList(dType);
	}

	@Override
	@CacheEvict(key="#form.toString()")
	public Object getList(DictForm form) throws Exception{
		return dao.getList(form);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object edit(DictForm form) throws Exception {
		return dao.edit(form);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object append(DictForm form) throws Exception {
		return dao.append(form);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object remove(DictForm form) throws Exception {
		return dao.remove(form);
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
