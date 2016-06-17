package com.gwamcc.aii.web.controller.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AppFuncObjForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AppFuncObjService;

/**
 * 应用功能控制器
 * @author 范大德
 *
 */

@Controller
@RequestMapping("/funcObj")
public class AppFuncObjController {

	@Autowired
	private AppFuncObjService service;

	/**
	 * 获取应用功能数据库对象对应的列表信息
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 * @throws Exception
	 */
	@RequestMapping("/GetList")
	@ResponseBody
	public PageDataForm getList(AppFuncObjForm form, PageForm page) throws Exception {
		return service.getList(form, page);
	}


	/**
	 * 保存应用功能数据库对象关联变更(新增或者修改)
	 * @param app
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String save(AppFuncObjForm app) throws Exception{
		if(app.getId()>-1){
			return service.edit(app).toString();
		}else{
			return service.append(app).toString();
		}
	}

	/**
	 * 有效/失效
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(AppFuncObjForm bean) throws Exception{
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
