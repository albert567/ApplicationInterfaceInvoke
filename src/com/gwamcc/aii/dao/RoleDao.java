package com.gwamcc.aii.dao;

import java.util.List;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleForm;

public interface RoleDao {

	List<DefaultForm> getList();

	Object getList(RoleForm role,PageForm page);

	Object edit(RoleForm role);

	Object append(RoleForm role);

	Object remove(int id);

}
