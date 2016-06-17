package com.gwamcc.aii.forms;

import java.util.Date;

import com.gwamcc.aii.util.sql2.anno.Field;
import com.gwamcc.aii.util.sql2.anno.Left;
import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoQuery;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

@Table("T_Log")
public class LogQueryForm extends DefaultForm {
	@PK
	@NoInsert
	@NoUpdate
	private long id;
	@NoInsert
	@NoUpdate
	@NoQuery
	@Field("T_Log.userID")
	@Left(table="[User].gwauth.dbo.tblUser",fields="UserName",on="tblUser.userId")
	private String userId;
	@Field("tblUser.UserName")
	public String userName;
	@NoInsert
	@NoUpdate
	private String dataUUID;
	@NoUpdate
	@NoInsert
	private Date logTime;
	@NoUpdate
	@NoInsert
	private String dataOperation;
	@NoInsert
	@NoUpdate
	private String logMsg;
	public long getId() {
		return id;
	}
	public LogQueryForm setId(long id) {
		this.id = id;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public LogQueryForm setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Date getLogTime() {
		return logTime;
	}
	public LogQueryForm setLogTime(Date logTime) {
		this.logTime = logTime;
		return this;
	}
	public String getDataOperation() {
		return dataOperation;
	}
	public LogQueryForm setDataOperation(String dataOperation) {
		this.dataOperation = dataOperation;
		return this;
	}
	public String getLogMsg() {
		return logMsg;
	}
	public LogQueryForm setLogMsg(String logMsg) {
		this.logMsg = logMsg;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDataUUID() {
		return dataUUID;
	}
	public void setDataUUID(String dataUUID) {
		this.dataUUID = dataUUID;
	}
}
