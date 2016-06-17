package com.gwamcc.aii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwamcc.aii.dao.TreeDao;
import com.gwamcc.aii.forms.TreeForm;
import com.gwamcc.aii.service.TreeService;
/**
 * 导航树服务实现类
 * @author 范大德
 *
 */
@Service
//@CacheConfig(cacheNames="tree")
public class TreeServiceImpl implements TreeService {
	@Autowired
    private TreeDao  dao;

	@Override
	//@Cacheable(key="#id")
	public List<TreeForm> getSubList(int id) {
		// TODO Auto-generated method stub
		return dao.getSubList(id);
	}

}
