package com.gwamcc.aii.forms;

import java.util.List;

public class LeftNodeForm extends DefaultForm{
	private int id;
	private int dataID;
	private String typeID;
	private String text;
	private String state;
	private String type;
	private String iconCls;//节点图标
	private String attributes;//节点跳转属性
	private List<LeftNodeForm> Children;
	
	public int getId() {
		return id;
	}
	public LeftNodeForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getText() {
		return text;
	}
	public LeftNodeForm setText(String text) {
		this.text = text;
		return this;
	}
	public String getState() {
		return state;
	}
	public LeftNodeForm setState(String state) {
		this.state = state;
		return this;
	}
	public String getType() {
		return type;
	}
	public LeftNodeForm setType(String type) {
		this.type = type;
		return this;
	}
	public List<LeftNodeForm> getChildren() {
		return Children;
	}
	public LeftNodeForm setChildren(List<LeftNodeForm> children) {
		Children = children;
		return this;
	}
	public int getDataID() {
		return dataID;
	}
	public LeftNodeForm setDataID(int dataID) {
		this.dataID = dataID;
		return this;
	}
	public String getTypeID() {
		return typeID;
	}
	public LeftNodeForm setTypeID(String typeID) {
		this.typeID = typeID;
		return this;
	}
	public String getAttributes() {
		return attributes;
	}
	public LeftNodeForm setAttributes(String attributes) {
		this.attributes = attributes;
		return this;
	}
	public String getIconCls() {
		return iconCls;
	}
	public LeftNodeForm setIconCls(String iconCls) {
		this.iconCls = iconCls;
		return this;
	}
}
