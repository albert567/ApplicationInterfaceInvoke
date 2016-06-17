package com.gwamcc.aii.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AttachmentReleaseDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.AttachmentReleaseForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.util.HttpKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.http.ZipFileItem;
import com.gwamcc.aii.util.http.ZipFileList;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ParamMap;

@Repository
public class AttachmentReleaseDaoImpl extends DefaultDao implements AttachmentReleaseDao {
	/**
	* Logger for this class
	*/
	private static final Logger logger = Logger.getLogger(AttachmentReleaseDaoImpl.class);

	@Override
	public Object list(String attachment, PageForm page) throws Exception {
		return queryPage(AttachmentReleaseForm.class, page, new String[]{"release desc"}, new ConditionDef(new Object[][]{
			{"attachment=:attachment"}
		}),new ParamMap().put("attachment",attachment));
	}

	@Override
	public Object remove(AttachmentReleaseForm bean) throws Exception {
		return MsgFrom.err("<==开发中==>");
	}

	@Override
	public Object upload(int attachmentId, MultipartFile[] files) throws Exception {
		return MsgFrom.err("<==开发中==>");
	}

	@Override
	public Object download(String[]uuids) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("download(AttachmentReleaseForm[]) - String[] uuids=" + StrKit.toString(uuids)); //$NON-NLS-1$
		}
		List<DefaultForm>list= queryList(AttachmentReleaseForm.class,new ConditionDef(new Object[][]{
			{"T_Attachment_Release.uuid in (:uuid)"}
		}),ParamMap.map("uuid", uuids));

		if(list.size()<=0){
			throw new Exception("请选择要下载的文件!");
		}

		if(list.size()==1){
			AttachmentReleaseForm form=(AttachmentReleaseForm)list.get(0);
			return  HttpKit.download("uploads\\"+form.getRealName(),form.getRelease()+"_"+form.getPath());
		}

		String name=((AttachmentReleaseForm)list.get(0)).getPath().substring(0, ((AttachmentReleaseForm)list.get(0)).getPath().lastIndexOf("."))+".zip";

		return HttpKit.zip(new ZipFileList(name).addList(list, new ZipFileList.OnListItemAdd<DefaultForm>() {

			@Override
			public ZipFileItem convert(List<DefaultForm> list, int index, DefaultForm item) {
				AttachmentReleaseForm releaseForm=(AttachmentReleaseForm)item;

				return new ZipFileItem().setPath("/uploads/")
						.setName(releaseForm.getRealName())
						.setDisplay(releaseForm.getRelease()+"_"+releaseForm.getPath());
			}

		}));
	}

}
