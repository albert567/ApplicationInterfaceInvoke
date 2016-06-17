package com.gwamcc.aii.dao;

import com.gwamcc.aii.forms.TopoNode;

public interface JTopoDao {

	TopoNode getSub(String type, int id, TopoNode pNode) throws Exception;

}
