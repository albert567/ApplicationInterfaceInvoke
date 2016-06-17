package com.gwamcc.aii.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.RoleRightDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.ApplicationForm;
import com.gwamcc.aii.forms.DataBaseForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.LoginUser;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RightForm;
import com.gwamcc.aii.forms.RoleForm;
import com.gwamcc.aii.forms.RoleRightForm;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;

import config.Res;

/**
 * 用户权限操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class RoleRightDaoImpl extends DefaultDao implements RoleRightDao {
	@Override
	public List<DefaultForm> getName(int roleId,int menuId){
		List<DefaultForm> list = queryList(RoleRightForm.class,
				new ConditionDef(new Object[][]{{"roleid=:roleid"},{"menuid=:menuid"}}),
				new ParamMap().put("roleid", roleId).put("menuid", menuId));
		List<DefaultForm> dataList = null;
		List<Integer> idList=new ArrayList<Integer>();
		for(DefaultForm dform:list){
			if (dform instanceof RoleRightForm) {
				RoleRightForm newForm = (RoleRightForm) dform;
				idList.add(newForm.getDataId());
			}
		}
		if(menuId==Res.MENU.APPLICATION){
			dataList = queryList(ApplicationForm.class,new ConditionDef(new Object[][]{{"T_Application.id not in (:appid)"}}),
					new ParamMap().put("appid", idList.toArray(new Integer[0])));
		}else if(menuId==Res.MENU.DATABASE){
			dataList = queryList(DataBaseForm.class,new ConditionDef(new Object[][]{{"T_DataBase.id not in (:dbid)"}}),
					new ParamMap().put("dbid", idList.toArray(new Integer[0])));
		}
		return dataList;
	}

	@Override
	public List<DefaultForm> getList(RoleRightForm form) {
		return queryList(RoleRightForm.class,new ConditionDef(new Object[][]{{"roleid=:roleid"},{"menuid=:menuid"}}),new ParamMap().put("roleid", form.getRoleId()).put("menuid", form.getMenuId()));
	}

	@Override
	public Object getList(RoleRightForm form,PageForm page) {
		try {
			ConditionDefBuilder builder=new ConditionDefBuilder();
			ParamMap map=new ParamMap();
			if(!StringUtils.isEmpty(form.getName())){
				builder.param("T_Application.name like :name  OR T_Database.name like :name");
				map.put("name", "%" + form.getName() + "%");
			}
			if(!StringUtils.isEmpty(form.getDescription())){
				builder.param("T_Application.description like :desc OR T_Database.description like :desc");
				map.put("desc", "%" + form.getDescription() + "%");
			}
			builder.param("T_RoleRight.roleId=:roleid");
			map.put("roleid", form.getRoleId());
			builder.param("T_RoleRight.menuId=:menuid");
			map.put("menuid", form.getMenuId());

			return queryPage(RoleRightForm.class, page, new String[] {"ID"},builder.define(),map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsgFrom.err("后台处理发生异常!");
	}

	@Override
	public Object edit(RoleRightForm form) {
		if(saveUpdate(form)) {
			return MsgFrom.info("修改成功!");
		} else
			return MsgFrom.err("修改失败!");
	}

	@Override
	public Object append(RoleRightForm form) {
		if( save(form)) {
			return MsgFrom.info("保存成功!");
		} else
			return MsgFrom.err("保存失败!");
	}

	@Override
	public Object remove(int id) {
		if(delete(new RoleRightForm().setId(id))){
			return MsgFrom.info("删除成功!");
		} else
			return MsgFrom.err("删除失败!");
	}

	@Override
	public String right(RoleRightForm form) throws Exception {

		LoginUser userDetails =(LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(form.getDataId()>-1){
			return queryCount(RoleRightForm.class, new ConditionDef(new Object[][]{
					{"RoleID IN (select roleID from T_RoleUser where UserID=:userid)"},
					{"MenuID=:menuid"},
					{"DataID=:dataid"}
				}),new ParamMap().put("userid",userDetails.getUserid())
					.put("menuid", form.getMenuId())
					.put("dataid", form.getDataId()))>0?RightForm.ALL.toString():RightForm.NONE.toString();
		}else{
			List<DefaultForm>roles=queryList(RoleForm.class,new ConditionDef(new Object[][]{
				{"id IN (select roleID from T_RoleUser where UserID=:userid)"}
			}), new ParamMap().put("userid", userDetails.getUserid()));
			for(DefaultForm def:roles){
				if (def instanceof RoleForm) {
					RoleForm role = (RoleForm) def;
					if(form.getMenuId()==Res.MENU.APPLICATION){
						if(role.getAppAdmin()==1){
							return RightForm.ALL.toString();
						}
					}else{
						if(role.getDbAdmin()==1){
							return RightForm.ALL.toString();
						}
					}
				}
			}
			return RightForm.NONE.toString();
		}
	}
}
