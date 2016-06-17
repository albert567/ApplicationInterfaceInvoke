package com.gwamcc.aii.web.controller.jtopo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.service.JTopoService;

/**
 * JTopo演示控制器
 * @author 范大德
 *
 */
@Controller
public class JTopoController {

	@Autowired
	private JTopoService service;

	/**
	 * 获取子节点
	 * @param id	父ID
	 * @return	子菜单列表
	 * @throws Exception
	 */
	@RequestMapping("/topo/sub/{typeText}/{typeId}")
	@ResponseBody
    public TopoNode getSub(@PathVariable String typeText,@PathVariable int typeId,TopoNode pNode) throws Exception {
         return service.getSub(typeText,typeId,pNode);
    }
}
