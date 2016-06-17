package com.gwamcc.aii.web.controller.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.MethodService;

/**
 * 接口方法控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/method")
public class MethodController {

	@Autowired
	private MethodService service;

	/**
	 * 获取方法给下拉框
	 * @param interfaceID  接口id
	 * @return 查询结果
	 */
	@RequestMapping("/getMethodName")
	@ResponseBody
    public List<DefaultForm> getMethodName(int InterfaceID) {
         return service.getMethodName(InterfaceID);
    }

	/**
	 * 获取方法列表
	 * @param interfaceID 接口id
	 * @return 查询结果
	 */
	@RequestMapping("/getMethodList")
	@ResponseBody
    public List<DefaultForm> getMethodList(int InterfaceID) {
         return service.getMethodList(InterfaceID);
    }

	/**
	 * 获取所有方法类型给下拉框
	 * @return 查询结果
	 */
	@RequestMapping("/getMethodType")
	@ResponseBody
    public List<DefaultForm> getMethodType() {
         return service.getMethodType();
    }

	/**
	 * 根据查询条件和分页信息获取方法列表
	 * @param interMethod	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/getMeList")
	@ResponseBody
	public PageDataForm getMethodList(InterMethodForm interMethod,PageForm page) {
	     return service.getMethodList(interMethod,page);
	}

	/**
	 * 保存接口方法变更(新增或者修改)
	 * @param interMethod
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String save(InterMethodForm interMethod){
		if(interMethod.getAppID()==0){
			return MsgFrom.err("请选择所属应用程序!").toString();
		}
		if(interMethod.getInterID()==0){
			return MsgFrom.err("请选择所属应用接口!").toString();
		}
		if(interMethod.getMethodID()>-1){
			return service.edit(interMethod).toString();
		}else{
			return service.append(interMethod).toString();
		}
	}

	/**
	 * 删除没有子级的接口方法
	 * @param methodID
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(int methodID){
		return service.remove(methodID).toString();
	}

	@RequestMapping(value="/Import",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file) throws Exception{
		return service.upload(file).toString();
	}
	@RequestMapping(value="/DownloadTemp",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download() throws Exception{
		return service.download();
	}


}
