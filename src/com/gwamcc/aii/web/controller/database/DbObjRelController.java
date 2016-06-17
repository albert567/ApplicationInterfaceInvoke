package com.gwamcc.aii.web.controller.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DbObjRelForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.DbObjRelService;

/**
 * 字段页面控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/objrel")
public class DbObjRelController {
	@Autowired
	private DbObjRelService service;

	/**
	 * 获取关联对象列表
	 * @return
	 */
	@RequestMapping(value="/getDbObj")
	@ResponseBody
	public List<DefaultForm>getDbObj(int objID){
		return service.getDbObj(objID);
	}
	/**
	 * 分页获取关联对象信息列表
	 * @param objRel 查询条件
	 * @param page	分页信息
	 * @return
	 */
	@RequestMapping(value="/getObjList")
	@ResponseBody
	public Object getDbObjList(DbObjRelForm objRel,PageForm page){
		return service.getDbObjList(objRel, page);
	}
	/**
	 * 保存字段变更(新增或者修改)
	 * @param objRel
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(DbObjRelForm objRel) throws Exception{
		if(objRel.getId()>0){
			return service.edit(objRel).toString();
		}else{
			return service.append(objRel).toString();
		}
	}

	/**
	 * 删除指定的关联对象信息
	 * @param relID	待删除的关联
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int relID){
		return service.remove(relID).toString();
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
