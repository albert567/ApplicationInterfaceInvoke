package com.gwamcc.aii.web.controller.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.forms.LogQueryParamForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.LogService;


/**
 * @author 范大德
 *
 */

@RequestMapping("/log")
@Controller
public class LogController {

	@Autowired
	private LogService service;

	/**
	 * 导入日志影响表列表
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/tabs/{uuid}")
	@ResponseBody
    public Object tabs(@PathVariable String uuid) throws Exception{
         return service.tabs(uuid);
    }

	/**
	 * 取所有的日志分类Tab
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tabs")
	@ResponseBody
    public Object tabs() throws Exception{
         return service.tabs();
    }


	/**
	 * 指定表格标题
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/title/{type}")
	@ResponseBody
    public Object title(@PathVariable String type) throws Exception{
         return service.title(type);
    }


	@RequestMapping("/list/u/{uuid}/{table}")
	@ResponseBody
    public Object listByUuid(@PathVariable String table,@PathVariable String uuid,PageForm page) throws Exception{
         return service.listByUuid(table,uuid,page);
    }
	@RequestMapping("/list")
	@ResponseBody
    public Object list(LogQueryParamForm query,PageForm page) throws Exception{
         return service.list(query,page);
    }
	@RequestMapping("/operations")
	@ResponseBody
    public Object operations() throws Exception{
         return service.operations();
    }
	@RequestMapping("/history/{datauuid}")
	@ResponseBody
    public Object history(@PathVariable String datauuid) throws Exception{
         return service.history(datauuid);
    }
	/**
	 * 回滚当前导入数据
	 * @param uuid 导入日志UUID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/rollback",method=RequestMethod.POST)
	@ResponseBody
	public Object rollback(String uuid) throws Exception{
		return service.rollback(uuid);
	}
}
