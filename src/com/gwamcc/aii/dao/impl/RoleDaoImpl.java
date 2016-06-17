package com.gwamcc.aii.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.RoleDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleForm;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;

/**
 * 数据字典操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class RoleDaoImpl extends DefaultDao implements RoleDao {

	@Override
	public List<DefaultForm> getList() {
		return queryList(RoleForm.class);
	}

	@Override
	public Object getList(RoleForm role,PageForm page) {
		try {
			ConditionDefBuilder builder=new ConditionDefBuilder();
			ParamMap map=new ParamMap();
			if(!StringUtils.isEmpty(role.getName())){
				builder.param("name like :name");
				map.put("name", "%" + role.getName() + "%");
			}
			if(!StringUtils.isEmpty(role.getDescription())){
				builder.param("description like :desc");
				map.put("desc", "%" + role.getDescription() + "%");
			}
			return queryPage(RoleForm.class, page, new String[] {"ID"},builder.define(),map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsgFrom.err("后台处理发生异常!");
	}

	@Override
	public Object edit(RoleForm role) {
		if(saveUpdate(role)) {
			return MsgFrom.info("修改成功!");
		} else
			return MsgFrom.err("修改失败!");
	}

	@Override
	public Object append(RoleForm role) {
		if( save(role)) {
			return MsgFrom.info("保存成功!");
		} else
			return MsgFrom.err("保存失败!");
	}

	@Override
	public Object remove(int id) {
		if(delete(new RoleForm().setId(id))){
			return MsgFrom.info("删除成功!");
		} else
			return MsgFrom.err("删除失败!");
	}
}
