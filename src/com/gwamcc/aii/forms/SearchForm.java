package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * 全文检索数据模型
 * @author 范大德
 *
 */
@Table(value="V_Search",hasUuid=false)
public class SearchForm  extends DefaultForm{
	private int id;//ID
	@PK
	private String tableID;//表名+ID
	private String tableName;//表名
	private int menuID;//菜单ID
	private String description;//描述
	@NoInsert
	@NoUpdate
	@NoQuery
	private String url;//跳转url
	@NoInsert
	@NoUpdate
	@NoQuery
	private String type;//类型
	private String name;//名称
	@NoInsert
	@NoUpdate
	@NoQuery
	private String src;//路径
	public String getTableID() {
		return tableID;
	}
	public SearchForm setTableID(String tableID) {
		this.tableID = tableID;
		return this;
	}
	public int getId() {
		return id;
	}
	public SearchForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getTableName() {
		return tableName;
	}
	public SearchForm setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	public int getMenuID() {
		return menuID;
	}
	public SearchForm setMenuID(int menuID) {
		this.menuID = menuID;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public SearchForm setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public SearchForm setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getType() {
		return type;
	}
	public SearchForm setType(String type) {
		this.type = type;
		return this;
	}
	public String getName() {
		return name;
	}
	public SearchForm setName(String name) {
		this.name = name;
		return this;
	}
	public String getSrc() {
		return src;
	}
	public SearchForm setSrc(String src) {
		this.src = src;
		return this;
	}
}
