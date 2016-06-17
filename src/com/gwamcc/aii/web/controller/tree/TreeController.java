package com.gwamcc.aii.web.controller.tree;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.TreeForm;
import com.gwamcc.aii.service.TreeService;

/**
 * 导航树控制器
 * @author 范大德
 *
 */
@Controller
public class TreeController {

	@Autowired
	private TreeService service;

	/**
	 * 获取子菜单
	 * @param id	父ID
	 * @return	子菜单列表
	 */
	@RequestMapping("/tree/getSubList/{id}")
	@ResponseBody
    public List<TreeForm> getSubList(@PathVariable int id) {
         return service.getSubList(id);
    }
}
