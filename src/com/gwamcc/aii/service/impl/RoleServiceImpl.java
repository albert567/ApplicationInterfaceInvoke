package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.RoleDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleForm;
import com.gwamcc.aii.service.RoleService;
/**
 * 角色服务实现类
 * @author 范大德
 *
 */
@Service
/*@CacheConfig(cacheNames="application")*/
public class RoleServiceImpl implements RoleService {
	@Autowired
    private RoleDao dao;

	@Override
	public List<DefaultForm> getList() {
		return dao.getList();
	}

	@Override
	public Object getList(RoleForm role,PageForm page) {
		return dao.getList(role,page);
	}

	@Override
	public Object edit(RoleForm role) {
		return dao.edit(role);
	}

	@Override
	public Object append(RoleForm role) {
		return dao.append(role);
	}

	@Override
	public Object remove(int id) {
		return dao.remove(id);
	}


}
