package com.gwamcc.aii.web.controller.leftmenu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.dao.impl.LeftMenuDaoImpl;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.service.LeftMenuService;

import config.Res;

/**
 * 菜单树页面控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/leftMenu")
public class LeftMenuController {

	@Autowired
	private LeftMenuService service;

	/**
	 * 获取菜单树
	 * @param id
	 * @param type
	 * @param typeID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAppList")
	@ResponseBody
    public List<DefaultForm> getAppList(int id,String type,String typeID) throws Exception {
		switch(id){
			case 101:
				return service.getAppList();
			case 102:
				return service.getDbList();
		}
		
		if(id==Res.MENU.APPLICATION){
			return service.getAppList();
		}else if(id==Res.MENU.DATABASE){
			return service.getDbList();
		}else if("App".equals(type)){
			return service.getAppMenu(id);
		}else if("FuncMenu".equals(type)){
			return service.getFuncList(id);
		}else if("Func".equals(type)){
			return service.getFunc(id);
		}else if("InterMenu".equals(type)){
			return service.getInterType(id);
		}else if("DbMenu".equals(type)){
			return service.getDbList(id);
		}else if("InterType".equals(type)){
			return service.getInterList(id, typeID);
		}else if("Inter".equals(type)){
			if(LeftMenuDaoImpl.methodInterType.equals(typeID))
				return service.getMethodList(id);
			else if(LeftMenuDaoImpl.dbInterType.equals(typeID)){
				return service.getProcedureList(id);
			}else{
				return null;
			}
		}else if("Method".equals(type)){
			return service.getMethodMenu(id);
		}else if("Db".equals(type)){
			return service.getObjType(id);
		}else if("ObjType".equals(type)){
			return service.getObjList(id, typeID);
		}else{
			return null;
		}
         
    }
}
