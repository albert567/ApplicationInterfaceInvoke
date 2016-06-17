package com.gwamcc.aii.forms;

import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

@Table("T_Attachment")
@QueryForm(title="附件")
public class AttachmentForm extends DefaultForm {
	@PK
	@NoInsert
	@NoUpdate
	private long id;
	@NoQuery
	@NoUpdate
	private String type;
	@NoQuery
	@NoUpdate
	private int	parentId;
	@Title("附件名称")
	private String path;
	@Left(table="T_Attachment_Release",
			on="UUID",fields={"Release","RealName"})
	private String lastRelease;

	@NoInsert
	@NoUpdate
	@Field("T_Attachment_Release.RealName")
	private String realName;

	@NoInsert
	@NoUpdate
	@Field("T_Attachment_Release.Release")
	private String release;

	@Title("文档类型")
	@Field("DocType")
	@Left(table="T_Dict",on="DKey",fields="DValue")
	private String docType;

	@Field("T_Dict.DValue")
	@NoInsert
	@NoUpdate
	private String docTypeName;

	public long getId() {
		return id;
	}
	public AttachmentForm setId(long id) {
		this.id = id;
		return this;
	}
	public String getType() {
		return type;
	}
	public AttachmentForm setType(String type) {
		this.type = type;
		return this;
	}
	public int getParentId() {
		return parentId;
	}
	public AttachmentForm setParentId(int parentId) {
		this.parentId = parentId;
		return this;
	}
	public String getPath() {
		return path;
	}
	public AttachmentForm setPath(String path) {
		this.path = path;
		return this;
	}
	public String getRealName() {
		return realName;
	}
	public AttachmentForm setRealName(String realName) {
		this.realName = realName;
		return this;
	}
	public String getDocType() {
		return docType;
	}
	public AttachmentForm setDocType(String docType) {
		this.docType = docType;
		return this;
	}
	public String getDocTypeName() {
		return docTypeName;
	}
	public AttachmentForm setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
		return this;
	}

	public String getLastRelease() {
		return lastRelease;
	}
	public AttachmentForm setLastRelease(String lastRelease) {
		this.lastRelease = lastRelease;
		return this;
	}
	public String getRelease() {
		return release;
	}
	public AttachmentForm setRelease(String release) {
		this.release = release;
		return this;
	}

}
