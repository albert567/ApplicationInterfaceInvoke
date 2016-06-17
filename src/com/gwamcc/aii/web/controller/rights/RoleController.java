package com.gwamcc.aii.web.controller.rights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleForm;
import com.gwamcc.aii.service.RoleService;

/**
 * 角色控制器
 * @author 范大德
 *
 */
@Controller
@RequestMapping("/rights/role")
public class RoleController {

	@Autowired
	private RoleService service;

	/**
	 * 获取列表
	 * 	用作下拉列表展示
	 * @return 名称列表
	 */
	@RequestMapping("/getRole")
	@ResponseBody
    public List<DefaultForm> getList() throws Exception {
         return service.getList();
    }
	/**
	 * 分页获取列表
	 * @param role	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/getList")
	@ResponseBody
    public Object getList(RoleForm role,PageForm page) throws Exception {
         return service.getList(role,page);
    }

	/**
	 * 保存变更(新增或者修改)
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(RoleForm role) throws Exception{
		if("on".equalsIgnoreCase(role.getApp())){
			role.setAppAdmin(1);
		}
		if("on".equalsIgnoreCase(role.getDb())){
			role.setDbAdmin(1);
		}
		if(role.getId()>0){
			return service.edit(role).toString();
		}else{
			return service.append(role).toString();
		}
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int id) throws Exception{
		return service.remove(id).toString();
	}

}
