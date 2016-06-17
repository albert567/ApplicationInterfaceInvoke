package com.gwamcc.aii.web.controller.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.gwamcc.aii.exception.ExcelImportException;
import com.gwamcc.aii.forms.MsgFrom;

/**
 * 异常控制器
 * @author 范大德
 *
 */

@Controller
@RequestMapping(value="/exception",produces="text/plain;charset=UTF-8")
public class ExceptionController{

	/**
	 * 404错误提示
	 * @return
	 */
	@RequestMapping("/400")
	@ResponseBody
	public Object err400(){
		return "数据提交失败!";
	}

	/**
	 * 未配置异常提示异常信息
	 * @return
	 */
	@RequestMapping("/error")
	@ResponseBody
	public Object error(){
		return MsgFrom.err(getException().getMessage()).toString();
	}


	@RequestMapping("/DuplicateKeyException")
	@ResponseBody
	public Object sqlerror(){
		if(getException().getMessage().indexOf("插入重复键")>0){
			return MsgFrom.err("数据已经存在,请检查您的录入!").toString();
		}
		return error();
	}


	/**
	 * 数据导入异常处理
	 * @return
	 */
	@RequestMapping("/excel-import")
	@ResponseBody
	public Object excelImport(){
		return ((ExcelImportException) getException()).getMsg().toString();
	}


	/**
	 * 获取捕获的异常信息
	 * @return
	 */
	private Exception getException(){
		return (Exception) RequestContextHolder.getRequestAttributes().getAttribute("exception", RequestAttributes.SCOPE_REQUEST);
	}

}
