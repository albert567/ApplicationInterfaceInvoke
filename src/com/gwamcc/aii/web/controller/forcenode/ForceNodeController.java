package com.gwamcc.aii.web.controller.forcenode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.service.ForceNodeService;

/**
 * 
 * @author 张亚平
 *
 */
@Controller
public class ForceNodeController {
	
	@Autowired
	private ForceNodeService service;

	
	@RequestMapping("/force/getForceData")
	@ResponseBody
    public List<DefaultForm> getForceData() {
         return service.getForceData();
    }
}
