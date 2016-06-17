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
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.ProcedureParamForm;
import com.gwamcc.aii.service.ProcedureParamService;

/**
 * 存储过程参数控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/proparam")
public class ProcedureParamController {
	@Autowired
	private ProcedureParamService service;

	/**
	 * 获取参数给下拉框
	 * @param objID  存储过程id
	 * @return 查询结果
	 */
	@RequestMapping(value="/getParamName")
	@ResponseBody
    public List<DefaultForm> getParamName(int objID) {
         return service.getParamName(objID);
    }

	/**
	 * 获取参数列表
	 * @param objID 存储过程id
	 * @return 查询结果
	 */
	@RequestMapping(value="/getParamListByID")
	@ResponseBody
    public List<DefaultForm> getParamList(int objID) {
         return service.getParamList(objID);
    }

	/**
	 * 获取所有参数类型给下拉框
	 * @return 查询结果
	 */
	@RequestMapping(value="/getParamType")
	@ResponseBody
    public List<DefaultForm> getParamType() {
         return service.getParamType();
    }

	/**
	 * 根据查询条件和分页信息获取参数列表
	 * @param proParam	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping(value="/getParamList")
	@ResponseBody
	public PageDataForm getParamList(ProcedureParamForm proParam,PageForm page) {
	     return service.getParamList(proParam, page);
	}

	/**
	 * 保存存储过程参数变更(新增或者修改)
	 * @param proParam
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(ProcedureParamForm proParam){
		if(proParam.getId()>-1){
			return service.edit(proParam).toString();
		}else{
			return service.append(proParam).toString();
		}
	}

	/**
	 * 删除存储过程参数
	 * @param paramID
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
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
