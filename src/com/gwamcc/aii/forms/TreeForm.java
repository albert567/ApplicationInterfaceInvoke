package com.gwamcc.aii.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航树节点实例
 * @author 范大德
 *
 */
public class TreeForm  extends DefaultForm{
	public static String STATE_CLOSED="closed";
	public static String STATE_OPEN="open";
	private int id;//节点ID
	private int dataID;
	private String type;
	private String text;//节点文本
	private String iconCls;//节点图标
	private String attributes;//节点跳转属性
	private List<TreeForm>children=null;//子节点
	private String url;
private String state=TreeForm.STATE_OPEN;//节点状态
	private boolean selected;


	public boolean isSelected() {
		return selected;
	}
	public TreeForm setSelected(boolean selected) {
		this.selected = selected;
		return this;
	}
	public String getState() {
		return state;
	}
	public TreeForm setState(String state) {
		this.state = state;
		return this;
	}
	public List<TreeForm> getChildren() {
		return children;
	}
	public TreeForm setChildren(List<TreeForm> children) {
		this.children = children;
		return this;
	}

	public TreeForm addChildren(TreeForm children){
		if(getChildren()==null){
			setChildren(new ArrayList<TreeForm>());
		}
		this.children.add(children);
		return this;
	}

	public TreeForm() {}
	public int getId() {
		return id;
	}
	public TreeForm setId(int id) {
		this.id = id;
		return this;
	}
	public String getText() {
		return text;
	}
	public TreeForm setText(String text) {
		this.text = text;
		return this;
	}
	public String getIconCls() {
		return iconCls;
	}
	public TreeForm setIconCls(String iconCls) {
		this.iconCls = iconCls;
		return this;
	}
	public String getAttributes() {
		return attributes;
	}
	public TreeForm setAttributes(String attributes) {
		this.attributes = attributes;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public TreeForm setUrl(String url) {
		this.url = url;
		return this;
	}
	public int getDataID() {
		return dataID;
	}
	public TreeForm setDataID(int dataID) {
		this.dataID = dataID;
		return this;
	}
	public String getType() {
		return type;
	}
	public TreeForm setType(String type) {
		this.type = type;
		return this;
	}
}
