package com.gwamcc.aii.util.jtopo.types;

public class LinkType  implements Cloneable{
	private int id;
	private NodeType type;
	private String color;
	private String text;
	private boolean dashed;
	public static LinkType LINK_APPLICATION_INTERFACE=new LinkType("应用接口","#0000E3",false);
	public static LinkType LINK_APPLICATION_TYPE=new LinkType("","#0000EE",false);
	public static LinkType LINK_APPLICATION_FUNCTION=new LinkType("应用功能","#0066CC",false);
	public static LinkType LINK_APPLICATION_DATABASE=new LinkType("应用数据库","#00CACA",false);
	public static LinkType LINK_INTERFACE_METHOD=new LinkType("接口方法","#00AEAE",false);
	public static LinkType LINK_INVOKE_METHOD_APPLICATION=new LinkType("应用调用","#02C874",true);
	public static LinkType LINK_INVOKE_METHOD_METHOD=new LinkType("接口方法调用","#02C874",true);
	public static LinkType LINK_DATABASE_OBJECT=new LinkType("数据库对象","#00BB00",false);
	public static LinkType LINK_OBJECT_METHOD=new LinkType("数据库对象与接口方法对应","#00CACA",false);

	public LinkType(String text,String color, boolean dashed) {
		super();
		this.text=text;
		this.color = color;
		this.dashed = dashed;
	}
	public String getColor() {
		return color;
	}
	public LinkType setColor(String color) {
		this.color = color;
		return this;
	}
	public boolean isDashed() {
		return dashed;
	}
	public LinkType setDashed(boolean dashed) {
		this.dashed = dashed;
		return this;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


	public LinkType create(NodeType type,int id){
		try {
			return ((LinkType) this.clone()).setId(id).setType(type);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return this;
		}
	}

	public LinkType setId(int id) {
		this.id = id;
		return this;
	}
	public int getId() {
		return id;
	}
	public NodeType getType() {
		return type;
	}
	public LinkType setType(NodeType type) {
		this.type = type;
		return this;
	}

}
