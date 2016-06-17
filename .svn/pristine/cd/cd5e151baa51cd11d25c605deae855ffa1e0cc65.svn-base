package com.gwamcc.aii.web.controller.dict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.service.DictService;
/**
 * 数据字典控制器
 * @author 范大德
 *
 */
@Controller
@RequestMapping(value="/Dict",method=RequestMethod.POST)
public class DictController {

	@Autowired
	private DictService service;

	/**
	 * 新增或者修改字典数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object save(DictForm form)throws Exception{
		if(form.getId()>-1){
			return service.edit(form).toString();
		}else{
			return service.append(form).toString();
		}
	}
	/**
	 * 失效/有效
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object remove(DictForm form) throws Exception{
		return service.remove(form).toString();
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
	@RequestMapping(value="/DownloadTemp",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE,method=RequestMethod.GET)
	public Object download() throws Exception{
		return service.download();
	}

	/**
	 * 获取有效的字典数据
	 * @param dtype	数据字典类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetType/{dtype}")
	@ResponseBody
	public Object validList(@PathVariable String dtype) throws Exception{
		return service.validList(dtype);
	}

	/**
	 * 获取数据字典列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/GetList",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object getList(DictForm form) throws Exception{
		return service.getList(form).toString();
	}


}
