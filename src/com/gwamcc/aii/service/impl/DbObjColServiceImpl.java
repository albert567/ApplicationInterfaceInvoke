package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DbObjColDao;
import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.DbObjColService;

/**
 * 数据库对象服务实现类
 * @author 张亚平
 *
 */
@Service
@CacheConfig(cacheNames="T_DataBaseObjCol")
public class DbObjColServiceImpl implements DbObjColService{
	@Autowired
    private DbObjColDao dao;

	@Override
	@Cacheable(key="#objID")
	public List<DefaultForm> getDbCol(int objID) {
		return dao.getDbCol(objID);
	}

	@Override
	@Cacheable(key="#colID")
	public List<DefaultForm> getColSrcList(int colID){
		return dao.getColSrcList(colID);
	}

	@Override
	//@Cacheable(key="#objID")
	public List<DefaultForm> getDbColList(int objID) {
		return dao.getDbColList(objID);
	}

	@Override
	//@Cacheable(key="#dbCol.toString()+#page.toString()")
	public Object getDbColList(DbObjColForm dbCol, PageForm page) {
		return dao.getDbColList(dbCol, page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm append(DbObjColForm dbCol) {
		return dao.append(dbCol);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm edit(DbObjColForm dbCol) {
		return dao.edit(dbCol);
	}

	@Override
	@CacheEvict(allEntries=true)
	public DefaultForm remove(int colID) {
		return dao.remove(colID);
	}

	@Override
	@Cacheable(key="#root.methodName",value={"T_DataBaseObjCol","T_Dict"})
	public Object colType() {
		return dao.colType();
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
