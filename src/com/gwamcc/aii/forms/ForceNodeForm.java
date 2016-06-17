package com.gwamcc.aii.forms;

import java.util.List;

/**
 * 数据展示节点对象
 * @author 张亚平
 *
 */
public class ForceNodeForm extends DefaultForm{
	private List<ForceAdjForm> adjacencies;
	private ForceNodeDataForm data;
	private String id;
	private String name;
	public List<ForceAdjForm> getAdjacencies() {
		return adjacencies;
	}
	public ForceNodeForm setAdjacencies(List<ForceAdjForm> adjacencies) {
		this.adjacencies = adjacencies;
		return this;
	}
	public ForceNodeDataForm getData() {
		return data;
	}
	public ForceNodeForm setData(ForceNodeDataForm data) {
		this.data = data;
		return this;
	}
	public String getId() {
		return id;
	}
	public ForceNodeForm setId(String id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ForceNodeForm setName(String name) {
		this.name = name;
		return this;
	}
	
	
}
