package com.gwamcc.aii.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.RoleUserDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DepartCurrForm;
import com.gwamcc.aii.forms.DepartUserForm;
import com.gwamcc.aii.forms.DepartmentForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleUserForm;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;

import config.Res;


/**
 * 角色用户操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class RoleUserDaoImpl extends DefaultDao implements RoleUserDao {
	@Override
	public List<DefaultForm> getDepartList(){
		return queryList(DepartmentForm.class,
				new String[]{"tblSection.SectionId",
						"tblSection.SectionName"},
				null,new ConditionDef(new Object[][]{{"CanDo.rightid in(:right)"}}),
				new ParamMap().put("right", Res.RIGHTID.APPLICATION_AND_DATABASE));
	}
	
	@Override
	public List<DefaultForm> getCurrDepartList(int roleId){
		return queryList(DepartCurrForm.class,
				new String[]{"tblSection.SectionId",
						"tblSection.SectionName"},
				null,new ConditionDef(new Object[][]{{"T_RoleUser.RoleId=:roleId"}}),
				new ParamMap().put("roleId", roleId));
	}
	
	@Override
	public List<DefaultForm> getUserList(int roleId,String sectionId){
		return queryList(RoleUserForm.class,new ConditionDef(new Object[][]{{"roleid=:roleid"},
			{"UserBelongsToSect.SectionId=:sectionid"}}),
				new ParamMap().put("roleid", roleId).put("sectionid", sectionId));
	}

	@Override
	public List<DefaultForm> getList(RoleUserForm form) {
		return queryList(RoleUserForm.class,new ConditionDef(new Object[][]{{"roleid=:roleid"}}),new ParamMap().put("roleid", form.getRoleId()));

	}

	@Override
	public Object getList(RoleUserForm form,PageForm page) {
		try {
			ConditionDefBuilder builder=new ConditionDefBuilder();
			ParamMap map=new ParamMap();
			if(!StringUtils.isEmpty(form.getSectionName())){
				builder.param("tblSection.SectionName like :sectionname");
				map.put("sectionname", "%" + form.getSectionName() + "%");
			}
			if(!StringUtils.isEmpty(form.getName())){
				builder.param("tblUser.userName like :name");
				map.put("name", "%" + form.getName() + "%");
			}
			if(!StringUtils.isEmpty(form.getDescription())){
				builder.param("tblUser.UserDescription like :desc");
				map.put("desc", "%" + form.getDescription() + "%");
			}
			return queryPage(RoleUserForm.class, page, new String[] {"ID"},builder.define(),map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsgFrom.err("后台处理发生异常!");
	}

	@Override
	public Object edit(RoleUserForm form) {
		if(saveUpdate(form)) {
			return MsgFrom.info("修改成功!");
		} else
			return MsgFrom.err("修改失败!");
	}

	@Override
	public Object append(RoleUserForm form) {
		if( save(form)) {
			return MsgFrom.info("保存成功!");
		} else
			return MsgFrom.err("保存失败!");
	}

	@Override
	public Object remove(int id) {
		if(delete(new RoleUserForm().setId(id))){
			return MsgFrom.info("删除成功!");
		} else
			return MsgFrom.err("删除失败!");
	}

	@Override
	public List<DefaultForm> sync(String sectionId) {
		return queryList(DepartUserForm.class,
				new String[]{"tblUser.userId",
						"tblUser.loginName",
						"tblUser.userName"},
				null,new ConditionDef(new Object[][]{
			{"CanDo.rightid in(:right)"},
			{"UserBelongsToSect.SectionId=:sectionid"}
		}),new ParamMap().put("right", Res.RIGHTID.APPLICATION_AND_DATABASE)
				.put("sectionid", sectionId));
		
	}
}
