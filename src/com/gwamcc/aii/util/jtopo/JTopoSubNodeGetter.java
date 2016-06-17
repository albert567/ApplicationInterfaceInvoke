package com.gwamcc.aii.util.jtopo;

import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.TopoNode;

public interface JTopoSubNodeGetter {
	public TopoNode getSub(DefaultDao dao,int id, TopoNode pNode) throws Exception;
}
