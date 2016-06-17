package com.gwamcc.aii.forms;

/**
 * 数据展示连线对象
 * @author 张亚平
 *
 */
public class ForceAdjForm extends DefaultForm{
	private int nodeTID;
	private int nodeFID;
	private String nodeTo;
	private String nodeFrom;
	private ForceAdjDataForm data;//连线data
	
	public String getNodeTo() {
		return nodeTo;
	}
	public ForceAdjForm setNodeTo(String nodeTo) {
		this.nodeTo = nodeTo;
		return this;
	}
	public String getNodeFrom() {
		return nodeFrom;
	}
	public ForceAdjForm setNodeFrom(String nodeFrom) {
		this.nodeFrom = nodeFrom;
		return this;
	}
	public ForceAdjDataForm getData() {
		return data;
	}
	public ForceAdjForm setData(ForceAdjDataForm data) {
		this.data = data;
		return this;
	}
	public int getNodeFID() {
		return nodeFID;
	}
	public ForceAdjForm setNodeFID(int nodeFID) {
		this.nodeFID = nodeFID;
		return this;
	}
	public int getNodeTID() {
		return nodeTID;
	}
	public ForceAdjForm setNodeTID(int nodeTID) {
		this.nodeTID = nodeTID;
		return this;
	}
	
}
