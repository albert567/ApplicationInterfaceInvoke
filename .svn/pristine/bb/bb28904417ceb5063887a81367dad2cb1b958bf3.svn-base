package com.gwamcc.aii.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.JTopoDao;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.service.JTopoService;
/**
 * ITopo实现类
 * @author 范大德
 *
 */
@Service
//@CacheConfig(cacheNames="tree")
public class JTopoServiceImpl implements JTopoService {
	@Autowired
    private JTopoDao  dao;

	@Override
	//@Cacheable(key="#id")
	public TopoNode getSub(String type,int id,TopoNode pNode) throws Exception {
		return dao.getSub(type,id,pNode);
	}

}
