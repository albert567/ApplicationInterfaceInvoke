package com.gwamcc.aii.util.sql2;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件定义生成类
 * @author 范大德
 *
 */
public class ConditionDefBuilder {

    private List<Object[]>defineList;

    public ConditionDefBuilder() {}

    /**
     * 添加一个条件
     * @param param
     * @return
     */
    public ConditionDefBuilder param(Object...param){
    	if(defineList==null)
    		defineList=new ArrayList<Object[]>();
    	defineList.add(param);
    	return this;
    }
    /**
     * 添加一个条件
     * @param param
     * @return
     */
    public ConditionDefBuilder add(Object...param){
    	return param(param);
    }

    /**
     * 生成条件定义对象
     * @return
     */
    public ConditionDef define(){
    	if(defineList==null){
    		return null;
    	}
    	return new ConditionDef(defineList.toArray(new Object[0][0]));
    }
    /**
     * 生成条件定义对象
     * @return
     */
    public ConditionDef build(){
    	return define();
    }
}