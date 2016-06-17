package com.gwamcc.aii.forms;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;

import com.gwamcc.aii.util.sql2.anno.NoInsert;
import com.gwamcc.aii.util.sql2.anno.NoUpdate;
import com.gwamcc.aii.util.sql2.anno.PK;
import com.gwamcc.aii.util.sql2.anno.Table;

@Table("T_Log")
public class LogForm extends DefaultForm {



	@PK
	@NoInsert
	@NoUpdate
	private long id;
	@NoUpdate
	private String userId;
	@NoUpdate
	@NoInsert
	private Date logTime;
	@NoUpdate
	private String dataTable;
	@NoUpdate
	private String dataUUID;
	@NoUpdate
	private String dataOperation;
	@NoUpdate
	private String logMsg;
	public long getId() {
		return id;
	}
	public LogForm setId(long id) {
		this.id = id;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public LogForm setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public Date getLogTime() {
		return logTime;
	}
	public LogForm setLogTime(Date logTime) {
		this.logTime = logTime;
		return this;
	}
	public String getDataTable() {
		return dataTable;
	}
	public LogForm setDataTable(String dataTable) {
		this.dataTable = dataTable;
		return this;
	}
	public String getDataUUID() {
		return dataUUID;
	}
	public LogForm setDataUUID(String dataUUID) {
		this.dataUUID = dataUUID;
		return this;
	}
	public String getDataOperation() {
		return dataOperation;
	}
	public LogForm setDataOperation(String dataOperation) {
		this.dataOperation = dataOperation;
		return this;
	}
	public String getLogMsg() {
		return logMsg;
	}
	public LogForm setLogMsg(String logMsg) {
		this.logMsg = logMsg;
		return this;
	}

	public static LogForm newLog(){
		LogForm log= new LogForm();
		log.setUuid(UUID.randomUUID().toString());

    	LoginUser user =(LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	log.setUserId(user.getUserid());
		return log;
	}

}
