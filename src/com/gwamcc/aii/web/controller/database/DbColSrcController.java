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

import com.gwamcc.aii.forms.DbColSrcForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.DbColSrcService;

/**
 * 字段源数据控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/colsrc")
public class DbColSrcController {
	@Autowired
	private DbColSrcService service;

	/**
	 * 获取字段源数据列表
	 * @return
	 */
	@RequestMapping(value="/getColSrc")
	@ResponseBody
	public List<DefaultForm>getColSrc(int colID){
		return service.getColSrc(colID);
	}

	/**
	 * 分页获取字段源数据信息列表
	 * @param colSrc 查询条件
	 * @param page	分页信息
	 * @return
	 */
	@RequestMapping(value="/getColSrcList")
	@ResponseBody
	public Object getColSrcList(DbColSrcForm colSrc,PageForm page){
		return service.getColSrcList(colSrc, page);
	}
	/**
	 * 保存字段源数据变更(新增或者修改)
	 * @param colSrc
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String save(DbColSrcForm colSrc) throws Exception{
		if(colSrc.getId()>0){
			return service.edit(colSrc).toString();
		}else{
			return service.append(colSrc).toString();
		}
	}

	/**
	 * 删除指定的字段源数据信息
	 * @param id 待删除的字段源数据id
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(int id){
		return service.remove(id).toString();
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
