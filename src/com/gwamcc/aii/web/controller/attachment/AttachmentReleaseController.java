package com.gwamcc.aii.web.controller.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AttachmentReleaseService;


/**
 * @author 范大德
 *
 */

@RequestMapping("/AttachmentRelease")
@Controller
public class AttachmentReleaseController {

	@Autowired
	private AttachmentReleaseService service;
	/**
	 * 附件版本列表
	 * @param type 附件类型
	 * @param id	数据id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/{attachment}")
	@ResponseBody
    public Object list(@PathVariable String attachment,PageForm page) throws Exception{
         return service.list(attachment,page);
    }



	/**
	 * 有效/失效
	 * @param app
	 * @return
	 * @throws Exception
	 */
	/*
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(AttachmentReleaseForm bean) throws Exception{
		return service.remove(bean).toString();
	}
	*/

	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	/*
	@RequestMapping(value="/Upload/{attachmentId}",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object upload(@PathVariable int attachmentId,@RequestParam MultipartFile[]files) throws Exception{
		return service.upload(attachmentId,files).toString();
	}
	*/


	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Download",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download(String...uuid) throws Exception{
		return service.download(uuid);
	}
}
