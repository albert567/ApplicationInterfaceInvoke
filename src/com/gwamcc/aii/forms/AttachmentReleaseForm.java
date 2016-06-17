package com.gwamcc.aii.forms;

import java.util.Date;

import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

@Table("T_Attachment_Release")
@QueryForm(title="附件版本")
public class AttachmentReleaseForm extends DefaultForm {
	@PK
	@NoInsert
	@NoUpdate
	private long id;
	@NoUpdate
	@Left(table="T_Attachment",on="UUID",fields={"path"})
	private String attachment;

	@NoUpdate
	@NoInsert
	private String release;
	private Date updateTime;

	@Field("updateBy")
	@Left(table="[User].gwauth.dbo.tblUser",on="userId",fields="userName")
	private String updateUser;

	@NoInsert
	@NoUpdate
	@Field("(case when updateBy='F2AC3371-B726-4120-8D74-04416BCF463E' then '系统管理员' else userName end) userName")
	private String updateBy;

	private String realName;

	@NoInsert
	@NoUpdate
	@Field("T_Attachment.Path")
	private String path;

	public long getId() {
		return id;
	}
	public AttachmentReleaseForm setId(long id) {
		this.id = id;
		return this;
	}

	public String getRealName() {
		return realName;
	}
	public AttachmentReleaseForm setRealName(String realName) {
		this.realName = realName;
		return this;
	}

	public String getAttachment() {
		return attachment;
	}
	public AttachmentReleaseForm setAttachment(String attachment) {
		this.attachment = attachment;
		return this;
	}
	public String getRelease() {
		return release;
	}
	public AttachmentReleaseForm setRelease(String release) {
		this.release = release;
		return this;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public AttachmentReleaseForm setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public AttachmentReleaseForm setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}
	public String getPath() {
		return path;
	}
	public AttachmentReleaseForm setPath(String path) {
		this.path = path;
		return this;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public AttachmentReleaseForm setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
		return this;
	}


}
