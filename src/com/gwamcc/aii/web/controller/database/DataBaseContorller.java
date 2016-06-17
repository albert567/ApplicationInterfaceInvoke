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

import com.gwamcc.aii.forms.DataBaseForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.DataBaseService;
/**
 * 数据库页面控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/database")
public class DataBaseContorller {
	@Autowired
	private DataBaseService service;

	/**
	 * 获取数据库名称列表
	 * @return
	 */
	@RequestMapping("/getDb")
	@ResponseBody
	public List<DefaultForm>getDB(){
		return service.getDb();
	}
	/**
	 * 分页获取数据库信息列表
	 * @param db	查询条件
	 * @param page	分页信息
	 * @return
	 */
	@RequestMapping("/getDbList")
	@ResponseBody
	public Object getDbList(DataBaseForm db,PageForm page){
		return service.getDbList(db, page);
	}

	/**
	 * 保存数据库变更(新增或者修改)
	 * @param db
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(DataBaseForm db) throws Exception{
		if(db.getId()>0){
			return service.edit(db).toString();
		}else{
			return service.append(db).toString();
		}
	}

	/**
	 * 删除指定的数据库信息
	 * @param dbID	待删除的数据库
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int DbID){
		System.out.println("DbID="+DbID);
		return service.remove(DbID).toString();
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

	@RequestMapping(value="/getForceData")
	@ResponseBody
	public List<DefaultForm> getForceData(int dbID) {
		// TODO Auto-generated method stub
		List<DefaultForm> list = service.getForceData(dbID);
		return list;
	}

}
