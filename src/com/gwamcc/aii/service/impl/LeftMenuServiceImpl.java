package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.LeftMenuDao;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.service.LeftMenuService;
/**
 * 菜单树服务实现类
 * @author 张亚平
 *
 */
@Service
public class LeftMenuServiceImpl implements LeftMenuService {
	@Autowired
    private LeftMenuDao dao;
	@Override
	public List<DefaultForm> getAppList() {
		return dao.getAppList();
	}
	@Override
	public List<DefaultForm> getInterType(int appID) {
		// TODO Auto-generated method stub
		return dao.getInterType(appID);
	}
	@Override
	public List<DefaultForm> getInterList(int appID, String interTypeID) {
		// TODO Auto-generated method stub
		return dao.getInterList(appID, interTypeID);
	}
	@Override
	public List<DefaultForm> getMethodList(int interID) {
		// TODO Auto-generated method stub
		return dao.getMethodList(interID);
	}
	@Override
	public List<DefaultForm> getProcedureList(int interID) {
		// TODO Auto-generated method stub
		return dao.getProcedureList(interID);
	}
	@Override
	public List<DefaultForm> getDbList() {
		// TODO Auto-generated method stub
		return dao.getDbList();
	}
	@Override
	public List<DefaultForm> getObjList(int dbID,String typeID) {
		// TODO Auto-generated method stub
		return dao.getObjList(dbID,typeID);
	}
	@Override
	public List<DefaultForm> getAppMenu(int appID){
		// TODO Auto-generated method stub
		return dao.getAppMenu(appID);
	}
	@Override
	public List<DefaultForm> getFuncList(int appID) {
		// TODO Auto-generated method stub
		return dao.getFuncList(appID);
	}
	@Override
	public List<DefaultForm>getFunc(int funcID){
		return dao.getFunc(funcID);
	}
	
	@Override
	public List<DefaultForm> getDbList(int appID) {
		// TODO Auto-generated method stub
		return dao.getDbList(appID);
	}
	@Override
	public List<DefaultForm> getMethodMenu(int methodID) {
		// TODO Auto-generated method stub
		return dao.getMethodMenu(methodID);
	}
	@Override
	public List<DefaultForm> getObjType(int dbID) {
		// TODO Auto-generated method stub
		return dao.getObjType(dbID);
	}
	
}
