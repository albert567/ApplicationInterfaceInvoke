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

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterProcedureForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.InterProcedureService;

/**
 * 数据库接口控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/interProc")
public class InterProcedureController {

	@Autowired
	private InterProcedureService service;

	/**
	 * 获取数据库接口名称列表
	 * @param interID	接口Id
	 * @return	结果列表
	 */
	@RequestMapping("/getDbObj")
	@ResponseBody
	public List<DefaultForm>getDbObj(int interID){
		return service.getDbObj(interID);
	}

	/**
	 * 获取存储过程列表
	 * @param interID 接口id
	 * @return
	 */
	@RequestMapping("/getObjList")
	@ResponseBody
	public List<DefaultForm>getObjList(int interID){
		return service.getObjList(interID);
	}

	/**
	 * 获取数据库接口列表信息
	 * @param form	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/getDbObjList")
	@ResponseBody
	public Object getDbObjList(InterProcedureForm form, PageForm page) {
		return service.getDbObjList(form, page);
	}
	/**
	 * 保存数据库接口变更(新增或者修改)
	 * @param interPro
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(InterProcedureForm interPro){
		if(interPro.getId()>-1){
			return service.edit(interPro).toString();
		}else{
			return service.append(interPro).toString();
		}
	}

	/**
	 * 失效/有效
	 * @param interProID
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int interProID){
		return service.remove(interProID).toString();
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
