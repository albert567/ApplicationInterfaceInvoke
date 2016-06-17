package com.gwamcc.aii.forms;

public class RightForm extends DefaultForm {
	public static RightForm ALL=new RightForm(true,true,true,true);
	public static RightForm NONE=new RightForm(false,false,false,false);
	private boolean a;
	private boolean i;
	private boolean d;
	private boolean u;

	public RightForm(boolean a, boolean i, boolean d, boolean u) {
		super();
		this.a = a;
		this.i = i;
		this.d = d;
		this.u = u;
	}
	public boolean isA() {
		return a;
	}
	public void setA(boolean a) {
		this.a = a;
	}
	public boolean isI() {
		return i;
	}
	public void setI(boolean i) {
		this.i = i;
	}
	public boolean isD() {
		return d;
	}
	public void setD(boolean d) {
		this.d = d;
	}
	public boolean isU() {
		return u;
	}
	public void setU(boolean u) {
		this.u = u;
	}



}
