package com.gwamcc.aii.web.controller.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.ApplicationForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.ApplicationService;

/**
 * 应用程序页面控制器
 * @author 范大德
 *
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	private ApplicationService service;

	/**
	 * 获取应用程序名称列表
	 * 	用作下拉列表展示
	 * @return 名称列表
	 */
	@RequestMapping("/getApp")
	@ResponseBody
    public List<DefaultForm> getApp() throws Exception {
         return service.getApp();
    }
	/**
	 * 分页获取应用程序信息列表
	 * @param app	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/getAppList")
	@ResponseBody
    public Object getAppList(ApplicationForm app,PageForm page) throws Exception {
		//System.out.println(app.getName());
         return service.getAppList(app,page);
    }

	/**
	 * 保存应用程序变更(新增或者修改)
	 * @param app
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(ApplicationForm app) throws Exception{
		if(app.getId()>0){
			return service.edit(app).toString();
		}else{
			return service.append(app).toString();
		}
	}

	/**
	 * 删除没有子级的应用程序
	 * @param app
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int applicationID) throws Exception{
		return service.remove(applicationID).toString();
	}

	/**
	 * 获取应用类型
	 * @return
	 */
	@RequestMapping("/appType")
	@ResponseBody
	public Object appType() throws Exception{
		return service.appType();
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
	@RequestMapping(value="/getForceData")
	@ResponseBody
	public List<DefaultForm> getForceData(int appID) {
		// TODO Auto-generated method stub
		List<DefaultForm> list = service.getForceData(appID);
		return list;
	}


}
