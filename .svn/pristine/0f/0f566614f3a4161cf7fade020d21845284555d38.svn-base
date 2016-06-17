package com.gwamcc.aii.web.controller.rights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleUserForm;
import com.gwamcc.aii.service.RoleUserService;

/**
 * 角色应用权限控制器
 * @author 范大德
 *
 */
@Controller
@RequestMapping("/rights/user")
public class RoleUserController {

	@Autowired
	private RoleUserService service;
	
	/**
	 * 获取列表
	 * 	用作添加或编辑角色人员时,部门下拉列表展示
	 * @return 部门列表
	 */
	@RequestMapping("/getDepartList")
	@ResponseBody
	public List<DefaultForm> getDepartList(){
		return service.getDepartList();
	}
	
	/**
	 * 获取列表
	 * 	用作查询角色人员时,部门下拉列表展示
	 * @return 部门列表
	 */
	@RequestMapping("/getCurrDepartList")
	@ResponseBody
	public List<DefaultForm> getCurrDepartList(int roleId){
		return service.getCurrDepartList(roleId);
	}
	
	/**
	 * 获取列表
	 * 	用作下拉列表展示
	 * @return 用户列表
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	public List<DefaultForm> getUserList(int roleId,String sectionId){
		return service.getUserList(roleId,sectionId);
	}

	/**
	 * 获取列表
	 * 	用作下拉列表展示
	 * @return 名称列表
	 */
	@RequestMapping("/sync")
	@ResponseBody
    public List<DefaultForm> sync(String sectionId) throws Exception {
         return service.sync(sectionId);
    }

	/**
	 * 获取列表
	 * 	用作下拉列表展示
	 * @return 名称列表
	 */
	@RequestMapping("/getAll")
	@ResponseBody
    public List<DefaultForm> getList(RoleUserForm form) throws Exception {
         return service.getList(form);
    }
	/**
	 * 分页获取列表
	 * @param role	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/getList")
	@ResponseBody
    public Object getList(RoleUserForm form,PageForm page) throws Exception {
         return service.getList(form,page);
    }

	/**
	 * 保存变更(新增或者修改)
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(RoleUserForm form) throws Exception{
		if(form.getId()>0){
			return service.edit(form).toString();
		}else{
			return service.append(form).toString();
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
