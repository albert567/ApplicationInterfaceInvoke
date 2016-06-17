package com.gwamcc.aii.web.controller.rights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.RoleRightForm;
import com.gwamcc.aii.service.RoleRightService;

import config.Res;
/**
 * 角色应用权限控制器
 * @author 范大德
 *
 */
@Controller
@RequestMapping("/rights/db")
public class RightDBController {

	@Autowired
	private RoleRightService service;

	/**
	 * 获取列表
	 * 	用作下拉列表展示
	 * @return 名称列表
	 */
	@RequestMapping("/getName")
	@ResponseBody
    public List<DefaultForm> getName(int roleId) throws Exception {
         return service.getName(roleId,Res.MENU.DATABASE);
    }
	
	/**
	 * 获取列表
	 * 	用作下拉列表展示
	 * @return 名称列表
	 */
	@RequestMapping("/getAll")
	@ResponseBody
    public List<DefaultForm> getList(RoleRightForm form) throws Exception {
		form.setMenuId(Res.MENU.DATABASE);
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
    public Object getList(RoleRightForm form,PageForm page) throws Exception {
		form.setMenuId(Res.MENU.DATABASE);
         return service.getList(form,page);
    }

	/**
	 * 保存变更(新增或者修改)
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(RoleRightForm form) throws Exception{
		form.setMenuId(Res.MENU.DATABASE);
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

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/right/{id}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String right(@PathVariable int id) throws Exception{
		RoleRightForm form= new RoleRightForm();
		form.setMenuId(Res.MENU.DATABASE);
		form.setDataId(id);
		return service.right(form);
	}
}
