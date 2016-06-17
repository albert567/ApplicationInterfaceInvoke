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

import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.DbObjColService;

/**
 * 字段页面控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/dbcol")
public class DbObjColController {
	@Autowired
	private DbObjColService service;

	/**
	 * 获取数字段名称列表
	 * @return
	 */
	@RequestMapping(value="/getDbCol")
	@ResponseBody
	public List<DefaultForm>getDbCol(int ObjID){
		return service.getDbCol(ObjID);
	}
	/**
	 * 获取字段源数据列表
	 * @param colID
	 * @return
	 */
	@RequestMapping(value="/getColSrc")
	@ResponseBody
	public List<DefaultForm> getColSrcList(int colID){
		return service.getColSrcList(colID);
	}
	/**
	 * 获取字段列表
	 * @return
	 */
	@RequestMapping(value="/getDbColList")
	@ResponseBody
	public List<DefaultForm>getDbColList(int ObjID){
		return service.getDbColList(ObjID);
	}
	/**
	 * 分页获取字段信息列表
	 * @param dbCol	查询条件
	 * @param page	分页信息
	 * @return
	 */
	@RequestMapping(value="/getColList")
	@ResponseBody
	public Object getDbObjList(DbObjColForm dbCol,PageForm page){
		return service.getDbColList(dbCol, page);
	}
	/**
	 * 保存字段变更(新增或者修改)
	 * @param db
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(DbObjColForm dbCol) throws Exception{
		if(dbCol.getColID()>0){
			return service.edit(dbCol).toString();
		}else{
			return service.append(dbCol).toString();
		}
	}

	/**
	 * 删除指定的字段信息
	 * @param dbObjID	待删除的数据库对象
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int colID){
		return service.remove(colID).toString();
	}
	/**
	 * 获取字段类型
	 * @return
	 */
	@RequestMapping(value="/getColType")
	@ResponseBody
    public Object colType(){
    	return service.colType();
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
