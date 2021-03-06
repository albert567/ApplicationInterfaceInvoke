package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DbColSrcDao;
import com.gwamcc.aii.forms.DbColSrcForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.DbColSrcService;

/**
 * 字段源数据服务实现类
 * @author 张亚平
 *
 */
@Service
@CacheConfig(cacheNames="T_ObjColSource")
public class DbColSrcServiceImpl implements DbColSrcService{
	@Autowired
    private DbColSrcDao dao;

	@Override
	@Cacheable(key="#colID")
	public List<DefaultForm> getColSrc(int colID) {
		// TODO Auto-generated method stub
		return dao.getColSrc(colID);
	}

	@Override
	@Cacheable(key="#colSrc.toString()+#page.toString()")
	public Object getColSrcList(DbColSrcForm colSrc, PageForm page) {
		// TODO Auto-generated method stub
		return dao.getColSrcList(colSrc, page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(DbColSrcForm colSrc) {
		// TODO Auto-generated method stub
		return dao.append(colSrc);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(DbColSrcForm colSrc) {
		// TODO Auto-generated method stub
		return dao.edit(colSrc);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int id) {
		// TODO Auto-generated method stub
		return dao.remove(id);
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
