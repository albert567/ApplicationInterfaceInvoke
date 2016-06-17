package com.gwamcc.aii.forms;
import java.util.Map;

import com.gwamcc.aii.util.sql2.Result;
import com.gwamcc.aii.util.sql2.RowMap;
import com.gwamcc.aii.util.sql2.RowMapper;
import com.gwamcc.aii.util.sql2.SQLUtils;

/**
 * 分页数据模板
 * @author 范大德
 *
 */
public class PageResultForm extends DefaultForm {
	private int total;//总行数
	private Result rows;//当前页数据

	public PageResultForm() {}

	public PageResultForm(int total, Result rows) {
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Result getRows() {
		return rows;
	}
	public void setRows(Result rows) {
		this.rows = rows;
	}
	public static PageResultForm create(PageDataForm form,RowMapper mapper) throws Exception{
		PageResultForm res=new PageResultForm();
		res.setTotal(form.getTotal());
		Result result=new Result();
		if(form.getRows()!=null){
			for(DefaultForm def:form.getRows()){
				Map<String, Object>map=SQLUtils.covertBeanToMap(def);
				result.add(mapper.mapper(RowMap.toRowMap(map)));
			}
		}
		res.setRows(result);
		return res;
	}

}
