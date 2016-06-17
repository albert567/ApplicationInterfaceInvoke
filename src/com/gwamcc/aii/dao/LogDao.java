package com.gwamcc.aii.dao;

import com.gwamcc.aii.forms.LogQueryParamForm;
import com.gwamcc.aii.forms.PageForm;

public interface LogDao {

	Object title(String type) throws Exception;

	Object listByUuid(String table,String uuid, PageForm page) throws Exception;

	Object list(LogQueryParamForm query, PageForm page) throws Exception;

	Object rollback(String uuid) throws Exception;

	Object tabs(String uuid)throws Exception;

	Object tabs()throws Exception;

	Object history(String datauuid)throws Exception;

	Object operations()throws Exception;

}
