package com.gwamcc.aii.web.controller.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwamcc.aii.service.TemplateService;

@Controller
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	private TemplateService service;
	@RequestMapping(value="/zip",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download(String[]name) throws Exception{
		return service.download(name);
	}

	@RequestMapping(value="/{name}",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object download(@PathVariable String name) throws Exception{
		return service.download(name);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object list() throws Exception{
		return service.list();
	}
}
