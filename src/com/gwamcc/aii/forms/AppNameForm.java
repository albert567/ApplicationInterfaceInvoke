package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 应用程序名称对象
 * @author 范大德
 *
 */
@Table("T_Application")
public class AppNameForm  extends DefaultForm{
	@PK
	private int id;//应用id
	private String name;//应用名称
	public AppNameForm() {	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
