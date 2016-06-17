package com.gwamcc.aii.util.http;

public class ZipFileItem {
	private String path;
	private String name;
	private String display;

	public ZipFileItem() {}


	public ZipFileItem(String path, String name, String display) {
		super();
		this.path = path;
		this.name = name;
		this.display = display;
	}


	public String getPath() {
		return path;
	}
	public ZipFileItem setPath(String path) {
		this.path = path;
		return this;
	}
	public String getDisplay() {
		return display;
	}
	public ZipFileItem setDisplay(String display) {
		this.display = display;
		return this;
	}
	public String getName() {
		return name;
	}
	public ZipFileItem setName(String name) {
		this.name = name;
		return this;
	}

}
