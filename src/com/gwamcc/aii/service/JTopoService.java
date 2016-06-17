package com.gwamcc.aii.service;

import com.gwamcc.aii.forms.TopoNode;

public interface JTopoService {
	TopoNode getSub(String type, int id, TopoNode pNode) throws Exception;

}
