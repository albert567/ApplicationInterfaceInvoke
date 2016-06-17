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

import com.gwamcc.aii.forms.AppCodeForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.service.AppCodeService;
/**
 * 应用系统代码结构控制器
 * @author 范大德
 *
 */
@Controller
@RequestMapping(value="/code",method=RequestMethod.POST)
public class AppCodeController {

	@Autowired
	private AppCodeService service;
	
	@RequestMapping("/GetCode")
	@ResponseBody
	public List<DefaultForm> getCode(int appID){
		return service.getCode(appID);
	}
	@RequestMapping("/GetRoot")
	@ResponseBody
	public List<DefaultForm> getRoot(int appID){
		return service.getRoot(appID);
	}
	@RequestMapping("/GetChild")
	@ResponseBody
	public List<DefaultForm> getChild(int codeID){
		return service.getChild(codeID);
	}

	/**
	 * 新增或者修改应用系统代码结构
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object save(AppCodeForm form)throws Exception{
		if(form.getId()>-1){
			return service.edit(form).toString();
		}else{
			return service.append(form).toString();
		}
	}
	/**
	 * 删除应用系统代码结构
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object remove(AppCodeForm form) throws Exception{
		return service.remove(form).toString();
	}

	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Import/{appId}",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file,@PathVariable int appId) throws Exception{
		return service.upload(file,appId).toString();
	}
	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DownloadTemp",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE,method=RequestMethod.GET)
	public Object download() throws Exception{
		return service.download();
	}

	/**
	 * 获取应用系统代码结构列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/GetList",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object getList(AppCodeForm form) throws Exception{
		return service.getList(form).toString();
	}


}
