package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.RoleUserDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleUserForm;
import com.gwamcc.aii.service.RoleUserService;
/**
 * 角色服务实现类
 * @author 范大德
 *
 */
@Service
/*@CacheConfig(cacheNames="application")*/
public class RoleUserServiceImpl implements RoleUserService {
	@Autowired
    private RoleUserDao dao;
	
	@Override
	public List<DefaultForm> getDepartList(){
		return dao.getDepartList();
	}
	@Override
	public List<DefaultForm> getCurrDepartList(int roleId){
		return dao.getCurrDepartList(roleId);
	}
	
	@Override
	public List<DefaultForm> getUserList(int roleId,String sectionId){
		return dao.getUserList(roleId, sectionId);
	}

	@Override
	public List<DefaultForm> getList(RoleUserForm form) {
		return dao.getList(form);
	}

	@Override
	public Object getList(RoleUserForm role,PageForm page) {
		return dao.getList(role,page);
	}

	@Override
	public Object edit(RoleUserForm role) {
		return dao.edit(role);
	}

	@Override
	public Object append(RoleUserForm role) {
		return dao.append(role);
	}

	@Override
	public Object remove(int id) {
		return dao.remove(id);
	}

	@Override
	public List<DefaultForm> sync(String sectionId) {

		return dao.sync(sectionId);
	}


}
