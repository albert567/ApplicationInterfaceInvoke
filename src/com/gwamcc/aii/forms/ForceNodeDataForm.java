package com.gwamcc.aii.forms;

import java.util.List;

/**
 * 数据展示节点数据对象
 * @author 张亚平
 *
 */
public class ForceNodeDataForm extends DefaultForm{
	private String $color ;//颜色
	private String $type;//图形
	private int $alpha;//透明度
	private String url;//路径
	private List<DefaultForm> args;//参数
	public String get$color() {
		return $color;
	}
	public ForceNodeDataForm set$color(String color) {
		this.$color = color;
		return this;
	}
	public String get$type() {
		return $type;
	}
	public ForceNodeDataForm set$type(String type) {
		this.$type = type;
		return this;
	}
	public int get$alpha() {
		return $alpha;
	}
	public ForceNodeDataForm set$alpha(int alpha) {
		this.$alpha = alpha;
		return this;
	}
	public List<DefaultForm> getArgs() {
		return args;
	}
	public ForceNodeDataForm setArgs(List<DefaultForm> args) {
		this.args = args;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public ForceNodeDataForm setUrl(String url) {
		this.url = url;
		return this;
	}
	
	
}
