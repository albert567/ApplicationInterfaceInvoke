package com.gwamcc.aii.web.controller.application;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.AppFuncCodeForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AppFuncCodeService;

/**
 * 应用功能源码控制器
 * @author 范大德
 *
 */

@Controller
@RequestMapping("/funcCode")
public class AppFuncCodeController {

	@Autowired
	private AppFuncCodeService service;
	
	/**
	 * 获取应用功能源码对应的列表信息
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 * @throws Exception
	 */
	@RequestMapping("/GetList")
	@ResponseBody
	public List<DefaultForm> getList(int appID,int funcID,int id) throws Exception {
		return service.getList(appID, funcID, id);
	}


	/**
	 * 保存应用功能源码关联变更(新增或者修改)
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String save(int funcID, int[] nodes) throws Exception{
		return service.save(funcID,nodes).toString();
	}
	/**
	 * 获取应用功能源码对应的列表信息
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 * @throws Exception
	 */
	@RequestMapping("/GetCodeList")
	@ResponseBody
	public PageDataForm getCodeList(AppFuncCodeForm form, PageForm page) throws Exception{
		return service.getCodeList(form, page);
	}
	
	/**
	 * 删除关联
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(AppFuncCodeForm form) throws Exception{
		return service.remove(form).toString();
	}

}
