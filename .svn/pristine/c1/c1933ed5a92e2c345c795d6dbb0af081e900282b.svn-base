package com.gwamcc.aii.util.form.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
	public @interface Title {
	public String value();
	public double width() default Double.NaN;
	public double rowspan() default Double.NaN;
	public double colspan() default Double.NaN;
	public String align() default "undefined";
	public String halign() default "undefined";
	public boolean sortable() default false;
	public String order() default "undefined";
	public boolean resizable() default false;//如果为true，允许列改变大小。
	public boolean fixed() default false;//如果为true，在"fitColumns"设置为true的时候阻止其自适应宽度。
	public boolean hidden() default false;//如果为true，则隐藏列。
	public boolean checkbox() default false;//如果为true，则显示复选框。该复选框列固定宽度。
	public String formatter() default "undefined";
	public String styler() default "undefined";
	public String sorter() default "undefined";
	public String editor() default "undefined";
}
