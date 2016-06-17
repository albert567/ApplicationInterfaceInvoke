package com.gwamcc.aii.dao;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AttachSearchForm;
import com.gwamcc.aii.forms.AttachmentForm;
import com.gwamcc.aii.forms.PageForm;

public interface AttachmentDao {
	Object getDocType(String type);

	Object list(AttachSearchForm form, PageForm page) throws Exception;

	Object remove(AttachmentForm bean) throws Exception;

	Object upload(String type, int id, String docType,String attachment,MultipartFile[] files) throws Exception;

	Object download(AttachmentForm bean) throws Exception;

	Object download(String[] names) throws Exception;

	Object count(String type, int appID, int id)throws Exception;
}
