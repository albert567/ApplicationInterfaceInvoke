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

import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AppInterService;

/**
 * 应用接口控制器
 * @author 范大德
 *
 */
@Controller
@RequestMapping("/appInter")
public class AppInterController {

	@Autowired
	private AppInterService service;

	/**
	 * 获取应用和接口对应的列表信息
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/GetAppInterList")
	@ResponseBody
	public PageDataForm getAppInterList(AppInterForm form, PageForm page) {
		return service.getAppInterList(form, page);
	}

	/**
	 * 获取指定应用程序所有的接口列表
	 * @param ApplicationID	应用程序id
	 * @return	查询结果
	 */
	@RequestMapping("/GetInterList")
	@ResponseBody
	public List<DefaultForm> getInterList(int ApplicationID) {
		return service.getInterList(ApplicationID);
	}
	/**
	 * 获取所有的接口类型
	 * @return	接口类型列表
	 */
	@RequestMapping("/GetInterType")
	@ResponseBody
	public List<DefaultForm> getInterType() {
		return service.getInterType();
	}

	/**
	 * 获取指定应用程序接口名称列表
	 * @param ApplicationID	应用程序Id
	 * @return	结果列表
	 */
	@RequestMapping("/GetInterName")
	@ResponseBody
	public List<DefaultForm>getInterName(int ApplicationID){
		return service.getInterName(ApplicationID);
	}

	/**
	 * 保存应用接口变更(新增或者修改)
	 * @param app
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(AppInterForm app){
		if(app.getAppID()==0){
			return MsgFrom.err("请选择所属应用程序!").toString();
		}
		if(app.getInterID()>-1){
			return service.edit(app).toString();
		}else{
			return service.append(app).toString();
		}
	}

	/**
	 * 失效/有效
	 * @param app
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int interfaceID){
		return service.remove(interfaceID).toString();
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
