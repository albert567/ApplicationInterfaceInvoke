package com.gwamcc.aii.dao.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AttachmentDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.AttachSearchForm;
import com.gwamcc.aii.forms.AttachmentForm;
import com.gwamcc.aii.forms.AttachmentReleaseForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SimpleTypeForm;
import com.gwamcc.aii.util.GWContext;
import com.gwamcc.aii.util.HttpKit;
import com.gwamcc.aii.util.http.ZipFileItem;
import com.gwamcc.aii.util.http.ZipFileList;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.sql2.Result;


/**
 * 数据字典操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class AttachmentDaoImpl extends DefaultDao implements AttachmentDao {

	private String root=System.getProperty("webapp.root")+"\\";
	private String uploadPath="uploads\\";
	@Override
	public Object getDocType(String type){
		String dtype = "";
		if("function".equals(type)){
			dtype="20";
		}else if("application".equals(type)){
			dtype="30";
		}
		return queryList(SimpleTypeForm.class, new ConditionDefBuilder().param("DType=:dtype and IsValid=1").define(),
				new ParamMap().put("dtype", dtype));
	}
	@Override
	public Object list(AttachSearchForm form,PageForm page) throws Exception {
		setPrintSql(true);
		int id = form.getId();
		int appID = form.getAppID();
		String docName = form.getDocName();
		String type = form.getType();
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap paramMap = new ParamMap();

		String[] typeVals = form.getDocType().split(",");
		String sql = "";
		if("function".equals(type)){
			if(id<0){
				sql = "Select ID From T_ApplicationFunction Where ApplicationID=:id";
				id = appID;
			}else{
				sql = "Select ID From dbo.F_GetFuncChild(:id)";
			}
			Result result = query(sql,new ParamMap().put("id", id));
			List<Integer> ids = new ArrayList<Integer>();
			while(result.next()){
				ids.add(result.getInt("ID"));
			}
			if(ids.size()<=0){
				ids.add(-1);
			}
			cond.param("parentID in (:id)");
			paramMap.put("id", ids);
		}else if("application".equals(type)){
			cond.param("parentID = :id");
			paramMap.put("id", appID);
		}
		if(docName!=null&&docName!=""){
			cond.param("path like :docName");
			paramMap.put("docName", "%"+docName+"%");
		}
		cond.param("type=:type").param("docType in (:docType)");
		paramMap.put("type", type).put("docType", typeVals);
		return queryPage(AttachmentForm.class, page, new String[]{"DocType","Path"},cond.define(), paramMap);
	}

	@Override
	public Object remove(AttachmentForm bean) throws Exception {
		bean=(AttachmentForm) queryEntity(AttachmentForm.class, new ConditionDef(new Object[][]{
			{"T_Attachment.uuid=:uuid"}
		}),new ParamMap().put("uuid", bean.getUuid()));
		new File(root+uploadPath+bean.getRealName()).delete();
		if(delete(bean)){
			return MsgFrom.info("删除成功!");
		}
		return MsgFrom.err("删除失败!");
	}

	@Override
	public Object upload(String type, int id, String docType,String attachment,MultipartFile[] files) throws Exception {
		boolean isNew="-1".equals(attachment);
		new File(root+uploadPath).mkdirs();
		for(MultipartFile file:files){
			String fileName=file.getOriginalFilename();
			String extName=fileName.indexOf(".")>0?fileName.substring(fileName.lastIndexOf(".")):"";
			String realName=UUID.randomUUID().toString()+extName;
			file.transferTo(new File(root+uploadPath+realName));
			String release=UUID.randomUUID().toString();
			AttachmentForm attachmentForm=null;
			if(isNew){
				attachmentForm=new AttachmentForm();
				attachment=UUID.randomUUID().toString();
				attachmentForm.setUuid(attachment);
				save(attachmentForm.setParentId(id).setType(type).setPath(fileName).setDocType(docType).setLastRelease(release));
			}else{
				attachmentForm=(AttachmentForm) queryEntity(AttachmentForm.class, new ConditionDef(new Object[][]{
					{"T_Attachment.uuid=:uuid"}
				}),new ParamMap().put("uuid", attachment));
				saveUpdate(attachmentForm.setLastRelease(release));
			}
			//保存文档版本
			AttachmentReleaseForm releaseForm=new AttachmentReleaseForm();
			releaseForm.setUuid(release);
			save(releaseForm.setAttachment(attachment).setRealName(realName).setUpdateUser(GWContext.getUser().getUserid()).setUpdateTime(new Date()));
			isNew=false;
		}
		return MsgFrom.info("成功上传 "+files.length+" 个文件");
	}

	@Override
	public Object download(AttachmentForm bean) throws Exception {
		bean=(AttachmentForm) queryEntity(AttachmentForm.class, new ConditionDef(new Object[][]{
			{"T_Attachment.uuid=:uuid"}
		}),new ParamMap().put("uuid", bean.getUuid()));
		return HttpKit.download(uploadPath+bean.getRealName(),bean.getPath());
	}

	@Override
	public Object download(String[] names) throws Exception{

		List<DefaultForm>list=queryList(AttachmentForm.class,new ConditionDef(new Object[][]{
			{"T_Attachment.uuid in (:uuid)"}
		}),new ParamMap().put("uuid", names));

		return HttpKit.zip(new ZipFileList("文档.zip").addList(list, new ZipFileList.OnListItemAdd<DefaultForm>(){

			@Override
			public ZipFileItem convert(List<DefaultForm> list, int index, DefaultForm item) {
				AttachmentForm form=(AttachmentForm)item;
				return new ZipFileItem(uploadPath,form.getRealName() , form.getPath());
			}

		}));


	}

	@Override
	public Object count(String type, int appID, int id) throws Exception {
		String sql = "";
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap paramMap = new ParamMap();
		
		if("function".equals(type)){
			if(id<0){
				sql = "Select ID From T_ApplicationFunction Where ApplicationID=:id";
				id = appID;
			}else{
				sql = "Select ID From dbo.F_GetFuncChild(:id)";
			}
			Result result = query(sql,new ParamMap().put("id", id));
			List<Integer> ids = new ArrayList<Integer>();
			while(result.next()){
				ids.add(result.getInt("ID"));
			}
			if(ids.size()<=0){
				ids.add(-1);
			}
			cond.param("parentID in (:id)");
			paramMap.put("id", ids);
		}else if("application".equals(type)){
			cond.param("parentID = :id");
			paramMap.put("id", appID);
		}
		cond.param("type=:type");
		paramMap.put("type", type);
		int count=queryCount(AttachmentForm.class, cond.define(), paramMap);
		return "{\"count\":"+count+"}";
	}

}
