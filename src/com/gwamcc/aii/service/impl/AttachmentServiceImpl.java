package com.gwamcc.aii.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AttachmentDao;
import com.gwamcc.aii.forms.AttachSearchForm;
import com.gwamcc.aii.forms.AttachmentForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.service.AttachmentService;
/**
 * 附件服务实现类
 * @author 范大德
 *
 */
@Service
@CacheConfig(cacheNames="T_Attachment")
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
    private AttachmentDao dao;

	@Override
	@Cacheable(key="#type",value={"T_Attachment","T_Dict"})
	public Object getDocType(String type){
		return dao.getDocType(type);
	}

	@Override
	@Cacheable(key="#form.toString()+#page.toString()")
	public Object list(AttachSearchForm form,PageForm page) throws Exception {
		return dao.list(form,page);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object remove(AttachmentForm bean) throws Exception {
		return dao.remove(bean);
	}

	@Override
	@CacheEvict(allEntries=true)
	public Object upload(String type, int id, String docType,String attachment,MultipartFile[] files) throws Exception {
		return dao.upload(type,id,docType,attachment,files);
	}

	@Override
	@Cacheable(key="#bean.toString()")
	public Object download(AttachmentForm bean) throws Exception {
		return dao.download(bean);
	}

	@Override
	@Cacheable(key="#names")
	public Object download(String[] names) throws Exception {
		return dao.download(names);
	}
	@Override
	@Cacheable(key="#type+#appID+#id")
	public Object count(String type, int appID, int id) throws Exception {
		return dao.count(type,appID,id);
	}





}
