package com.gwamcc.aii.forms;

/**
 * 数据展示节点数据对象
 * @author 张亚平
 *
 */
public class SpaceNodeDataForm extends DefaultForm{
	private String type;//节点类型
	private int nodeID;//节点ID
	private String url;//路径
	
	public String getUrl() {
		return url;
	}

	public SpaceNodeDataForm setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getType() {
		return type;
	}

	public SpaceNodeDataForm setType(String type) {
		this.type = type;
		return this;
	}

	public int getNodeID() {
		return nodeID;
	}

	public SpaceNodeDataForm setNodeID(int nodeID) {
		this.nodeID = nodeID;
		return this;
	}
	
}
