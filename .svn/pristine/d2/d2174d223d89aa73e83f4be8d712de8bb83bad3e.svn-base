package com.gwamcc.aii.web.controller.application;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AppFuncService;

/**
 * 应用功能控制器
 * @author 范大德
 *
 */

@Controller
@RequestMapping("/appFunc")
public class AppFuncController {

	@Autowired
	private AppFuncService service;

	/**
	 * 获取应用和功能对应的列表信息
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 * @throws Exception
	 */
	@RequestMapping("/GetList")
	@ResponseBody
	public PageDataForm getList(AppFuncForm form, PageForm page) throws Exception {
		return service.getList(form, page);
	}

	/**
	 * 获取指定应用程序所有的功能列表
	 * @param ApplicationID	应用程序id
	 * @return	查询结果
	 * @throws Exception
	 */
	@RequestMapping("/GetFuncList/{ApplicationID}")
	@ResponseBody
	public List<DefaultForm> getFuncList(@PathVariable int ApplicationID) throws Exception {
		return service.getFuncList(ApplicationID);
	}

	/**
	 * 获取指定应用程序功能名称列表
	 * @param ApplicationID	应用程序Id
	 * @return	结果列表
	 * @throws Exception
	 */
	@RequestMapping("/GetName/{ApplicationID}")
	@ResponseBody
	public List<DefaultForm>getInterName(@PathVariable int ApplicationID) throws Exception{
		return service.getName(ApplicationID);
	}

	/**
	 * 保存应用功能变更(新增或者修改)
	 * @param app
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String save(AppFuncForm app) throws Exception{
		if(app.getAppID()==0){
			return MsgFrom.err("请选择所属应用程序!").toString();
		}
		if(app.getId()>-1){
			return service.edit(app).toString();
		}else{
			return service.append(app).toString();
		}
	}

	/**
	 * 有效/失效
	 * @param app
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(AppFuncForm bean) throws Exception{
		return service.remove(bean).toString();
	}

	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Import",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file) throws Exception{
		return service.upload(file).toString();
	}
	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DownloadTemp",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download() throws Exception{
		return service.download();
	}

}
