package com.gwamcc.aii.web.controller.param;

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
import com.gwamcc.aii.forms.MethodParamForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.ParamService;

/**
 * 方法参数控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/param")
public class MethodParamController {
	@Autowired
	private ParamService service;

	/**
	 * 获取参数给下拉框
	 * @param methodID  方法id
	 * @return 查询结果
	 */
	@RequestMapping("/getParamName")
	@ResponseBody
    public List<DefaultForm> getParamName(int MethodID) {
         return service.getParamName(MethodID);
    }

	/**
	 * 获取参数列表
	 * @param methodID 方法id
	 * @return 查询结果
	 */
	@RequestMapping("/getParamList")
	@ResponseBody
    public List<DefaultForm> getParamList(int MethodID) {
         return service.getParamList(MethodID);
    }

	/**
	 * 获取所有参数类型给下拉框
	 * @return 查询结果
	 */
	@RequestMapping("/getParamType")
	@ResponseBody
    public List<DefaultForm> getParamType() {
         return service.getParamType();
    }

	/**
	 * 根据查询条件和分页信息获取参数列表
	 * @param methodParam	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/getPaList")
	@ResponseBody
	public PageDataForm getMethodList(MethodParamForm methodParam,PageForm page) {
	     return service.getParamList(methodParam, page);
	}

	/**
	 * 保存方法参数变更(新增或者修改)
	 * @param methodParam
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String save(MethodParamForm methodParam){
		if(methodParam.getAppID()==0){
			return MsgFrom.err("请选择所属应用程序!").toString();
		}
		if(methodParam.getInterID()==0){
			return MsgFrom.err("请选择所属应用接口!").toString();
		}
		if(methodParam.getMethodID()==0){
			return MsgFrom.err("请选择所属应用接口!").toString();
		}
		if(methodParam.getParamID()>-1){
			return service.edit(methodParam).toString();
		}else{
			return service.append(methodParam).toString();
		}
	}

	/**
	 * 删除方法参数
	 * @param paramID
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(int paramID){
		return service.remove(paramID).toString();
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
