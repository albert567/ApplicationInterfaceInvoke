package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.RoleRightDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleRightForm;
import com.gwamcc.aii.service.RoleRightService;
/**
 * 角色服务实现类
 * @author 范大德
 *
 */
@Service
/*@CacheConfig(cacheNames="application")*/
public class RoleRightServiceImpl implements RoleRightService {
	@Autowired
    private RoleRightDao dao;

	public List<DefaultForm> getName(int roleId,int menuId){
		return dao.getName(roleId,menuId);
	}
	
	@Override
	public List<DefaultForm> getList(RoleRightForm form) {
		return dao.getList(form);
	}

	@Override
	public Object getList(RoleRightForm role,PageForm page) {
		return dao.getList(role,page);
	}

	@Override
	public Object edit(RoleRightForm role) {
		return dao.edit(role);
	}

	@Override
	public Object append(RoleRightForm role) {
		return dao.append(role);
	}

	@Override
	public Object remove(int id) {
		return dao.remove(id);
	}

	@Override
	public String right(RoleRightForm form) throws Exception {
		return dao.right(form);
	}


}
