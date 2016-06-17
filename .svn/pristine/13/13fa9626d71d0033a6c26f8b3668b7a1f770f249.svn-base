package com.gwamcc.aii.dao;

import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.forms.AttachmentReleaseForm;
import com.gwamcc.aii.forms.PageForm;

public interface AttachmentReleaseDao {

	Object list(String attachment, PageForm page) throws Exception;

	Object remove(AttachmentReleaseForm bean)throws Exception;

	Object upload(int attachmentId, MultipartFile[] files)throws Exception;

	Object download(String[]uuids)throws Exception;

}
