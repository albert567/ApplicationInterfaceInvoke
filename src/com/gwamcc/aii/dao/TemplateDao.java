package com.gwamcc.aii.dao;

public interface TemplateDao {

	Object download(String[] names) throws Exception;


	Object download(String name) throws Exception;

	Object list() throws Exception;

}
