package com.gwamcc.aii.web.controller.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AttachSearchForm;
import com.gwamcc.aii.forms.AttachmentForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AttachmentService;


/**
 * @author 范大德
 *
 */

@RequestMapping("/Attachment")
@Controller
public class AttachmentController {

	@Autowired
	private AttachmentService service;
	/**
	 * 文档类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDocType/{type}")
	@ResponseBody
	public Object getDocType(@PathVariable String type){
		return service.getDocType(type);
	}

	/**
	 * 附件列表
	 * @param type 附件类型
	 * @param id	数据id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/count/{type}/{appID}/{id}")
	@ResponseBody
    public Object count(@PathVariable String type,@PathVariable int appID,@PathVariable int id) throws Exception{
         return service.count(type,appID,id);
    }


	/**
	 * 附件列表
	 * @param type 附件类型
	 * @param id	数据id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
    public Object list(AttachSearchForm form,
    		PageForm page) throws Exception{
        return service.list(form,page);
    }



	/**
	 * 有效/失效
	 * @param app
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(AttachmentForm bean) throws Exception{
		return service.remove(bean).toString();
	}

	/**
	 * 导入数据
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Upload/{type}/{id}/{attachment}",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public Object upload(@PathVariable String type,@PathVariable int id,@RequestParam String docType,@PathVariable String attachment,@RequestParam MultipartFile[]files) throws Exception{
		return service.upload(type,id,docType,attachment,files).toString();
	}



	/**
	 * 下载模板
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/Download",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download(AttachmentForm bean) throws Exception{
		return service.download(bean);
	}

	/**
	 * 打包下载模板
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/zip",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download(String[] name) throws Exception{
		return service.download(name);
	}
}
