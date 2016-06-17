package com.gwamcc.aii.forms;

import java.util.List;

/**
 * 数据展示节点对象
 * @author 张亚平
 *
 */
public class SpaceNodeForm extends DefaultForm{
	private List<SpaceNodeForm> children;
	private SpaceNodeDataForm data;
	private String id;
	private String name;
	
	public SpaceNodeDataForm getData() {
		return data;
	}
	public SpaceNodeForm setData(SpaceNodeDataForm data) {
		this.data = data;
		return this;
	}
	public String getId() {
		return id;
	}
	public SpaceNodeForm setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public SpaceNodeForm setName(String name) {
		this.name = name;
		return this;
	}
	public List<SpaceNodeForm> getChildren() {
		return children;
	}
	public SpaceNodeForm setChildren(List<SpaceNodeForm> children) {
		this.children = children;
		return this;
	}
	
	
}
