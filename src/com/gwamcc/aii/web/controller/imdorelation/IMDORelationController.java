package com.gwamcc.aii.web.controller.imdorelation;



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
import com.gwamcc.aii.forms.IMDORelationForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.IMDORelationService;

/**
 * 接口方法与数据库对象关系控制器
 * @author 范大德
 *
 */

@Controller
@RequestMapping(value="/IMDORelation",method=RequestMethod.POST)
public class IMDORelationController {

	@Autowired
	private IMDORelationService service;

	/**
	 * 根据数据库对象ID获取方法列表
	 * @param ObjID
	 * @return
	 */
	@RequestMapping("/getMethodByObjID")
	@ResponseBody
	public List<DefaultForm> getMethodByObjID(int ObjID){
		return service.getMethodByObjID(ObjID);
	}

	/**
	 * 根据方法ID获取数据库对象列表
	 * @param methodID
	 * @return
	 */
	@RequestMapping("/getObjByMeID")
	@ResponseBody
	public List<DefaultForm> getObjByMeID(int MethodID){
		return service.getObjByMeID(MethodID);
	}

	/**
	 *
	 * @param form
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/GetList")
	@ResponseBody
	public PageDataForm getList(IMDORelationForm form, PageForm page) throws Exception {
		return service.getList(form, page);
	}


	/**
	 * 保存关系变更(新增或者修改)
	 * @param app
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String save(IMDORelationForm app) throws Exception{
		if(app.getAppID()==0){
			return MsgFrom.err("请选择所属应用程序!").toString();
		}
		if(app.getId()>-1){
			return service.edit(app).toString();
		}else{
			return service.append(app).toString();
		}
	}

	/**
	 * 有效/失效
	 * @param app
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String remove(IMDORelationForm bean) throws Exception{
		return service.remove(bean).toString();
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


}
