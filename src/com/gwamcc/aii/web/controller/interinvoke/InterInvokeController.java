package com.gwamcc.aii.web.controller.interinvoke;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.InterInvokeForm;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.InterInvokeService;

/**
 * 应用接口调用控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping(value="/interInvoke")
public class InterInvokeController {
	@Autowired
	private InterInvokeService service;

	/**
	 * 根据查询条件和分页信息获取方法列表
	 * @param interMethod	查询条件
	 * @param page	分页信息
	 * @return	查询结果
	 */
	@RequestMapping("/getInterInvokeList")
	@ResponseBody
	public PageDataForm getInterInvokeList(InterInvokeForm interInvoke,PageForm page) {
	    return service.getInterInvokeList(interInvoke,page);
	}

	/**
	 * 保存应用接口调用变更(新增或者修改)
	 * @param interMethod
	 * @return
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String save(InterInvokeForm interInvoke){
		if(interInvoke.getInterInvokeID()>-1){
			return service.edit(interInvoke).toString();
		}else{
			return service.append(interInvoke).toString();
		}
	}

	/**
	 * 删除没有子级的应用接口
	 * @param interInvokeID
	 * @return
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(int interInvokeID){
		return service.remove(interInvokeID).toString();
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
