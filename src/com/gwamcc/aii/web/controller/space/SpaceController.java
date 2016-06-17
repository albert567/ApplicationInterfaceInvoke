package com.gwamcc.aii.web.controller.space;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.service.SpaceNodeService;

/**
 * 接口方法控制器
 * @author 张亚平
 *
 */
@Controller
@RequestMapping("/space")
public class SpaceController {

	@Autowired
	private SpaceNodeService service;

	@RequestMapping(value="/getForceData")
	@ResponseBody
	public Object getForceData(int appID) {
		// TODO Auto-generated method stub
		DefaultForm list = service.getForceData(appID);
		return list;
	}
	
	@RequestMapping(value="/getDbData")
	@ResponseBody
	public Object getDbData(int dbID) {
		// TODO Auto-generated method stub
		DefaultForm list = service.getDbData(dbID);
		return list;
	}
}
