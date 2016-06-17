package com.gwamcc.aii.forms;

import java.util.ArrayList;
import java.util.List;

import com.gwamcc.aii.util.jtopo.types.NodeType;

public class TopoNode {
	public static int NORMAL=0;
	public static int HIDDEN=1;
	public static int HIDDEN_SRC=-1;
	public static int REDRAW=2;

	private int id;
	//private String parent;
	private String name;
	private NodeType type;
	private String group;
	private List<TopoNode>sub;
	//private List<String>remove=new ArrayList<String>();

	private int method;

	public int getId() {
		return id;
	}
	public TopoNode setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public TopoNode setName(String name) {
		this.name = name;
		return this;
	}
	public NodeType getType() {
		return type;
	}
	public TopoNode setType(NodeType type) {
		this.type = type;
		return this;
	}

	public List<TopoNode> getSub() {
		return sub;
	}
	public TopoNode setSub(List<TopoNode> sub) {
		this.sub=new ArrayList<TopoNode>();
		 for(TopoNode node:sub){
			 if(!this.sub.contains(node)){
				 this.sub.add(node);
			 }
		 }
		return this;
	}

	public int getMethod() {
		return method;
	}
	public TopoNode setMethod(int method) {
		this.method = method;
		return this;
	}
/*
	public List<String> getRemove() {
		return remove;
	}

	public TopoNode setRemove(List<String> remove) {
		this.remove = remove;
		return this;
	}

	public TopoNode addReomve(String remove) {
		if(this.remove==null)
			this.remove=new ArrayList<String>();
		this.remove.add(remove);
		return this;
	}
	public TopoNode addToReomve(TopoNode node) {
		node.addReomve(this.getUID());
		return this;
	}*/

	public String getGroup() {
		return group;
	}
	public TopoNode setGroup(String group) {
		this.group = group;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj!=null&&obj instanceof TopoNode) {
			TopoNode nodeObj = (TopoNode) obj;
			return this.getUID().equals(nodeObj.getUID());
		}
		return false;
	}

	public String getUID(){
		return this.getType().toString()+this.getId();
	}
}
