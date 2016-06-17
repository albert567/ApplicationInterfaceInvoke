package com.gwamcc.aii.forms;

/**
 * 接口方法参数类型对象
 * @author 张亚平
 * 暂时不用
 */
public class ParamTypeForm extends DefaultForm{
	private String id;//主键ID
	private String name;//名称
	public ParamTypeForm() {	}
	public String getId() {
		return id;
	}
	public ParamTypeForm setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ParamTypeForm setName(String name) {
		this.name = name;
		return this;
	}

}
