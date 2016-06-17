package com.gwamcc.aii.web.controller.database;

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

import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.DataBaseObjService;

/**
 * 数据库页面控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/dbobj")
public class DataBaseObjController {
	@Autowired
	private DataBaseObjService service;

	/**
	 * 获取存储过程列表
	 * @return
	 */
	@RequestMapping(value="/getProcedure")
	@ResponseBody
	public List<DefaultForm>getProcedure(int DbID){
		return service.getProcedure(DbID);
	}
	/**
	 * 获取数据库对象名称列表
	 * @return
	 */
	@RequestMapping(value="/getDbObj")
	@ResponseBody
	public List<DefaultForm>getDbObj(int DbID){
		return service.getDbObj(DbID);
	}
	/**
	 * 获取数据库对象列表
	 * @return
	 */
	@RequestMapping(value="/getDbObjList")
	@ResponseBody
	public List<DefaultForm>getDbObjList(int DbID){
		return service.getDbObjList(DbID);
	}
	/**
	 * 分页获取数据库对象信息列表
	 * @param dbObj	查询条件
	 * @param page	分页信息
	 * @return
	 */
	@RequestMapping(value="/getObjList")
	@ResponseBody
	public Object getDbObjList(DataBaseObjForm dbObj,PageForm page){
		return service.getDbObjList(dbObj, page);
	}
	/**
	 * 保存数据库对象变更(新增或者修改)
	 * @param db
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(DataBaseObjForm dbObj) throws Exception{
		if(dbObj.getObjID()>0){
			return service.edit(dbObj).toString();
		}else{
			return service.append(dbObj).toString();
		}
	}

	/**
	 * 删除指定的数据库对象信息
	 * @param dbObjID	待删除的数据库对象
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int objID){
		return service.remove(objID).toString();
	}
	/**
	 * 获取数据库对象类型
	 * @return
	 */
	@RequestMapping(value="/getObjType")
	@ResponseBody
    public Object objType(){
    	return service.objType();
    }

	@RequestMapping(value="/Import",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file) throws Exception{
		return service.upload(file).toString();
	}

	@RequestMapping(value="/ImportPDM/{db}",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object uploadPdm(@PathVariable int db,@RequestParam MultipartFile file) throws Exception{
		return service.uploadPdm(db,file).toString();
	}

	@RequestMapping(value="/DownloadTemp",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download() throws Exception{
		return service.download();
	}

}
