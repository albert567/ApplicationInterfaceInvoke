package com.gwamcc.aii.util.sql2;
/**
 * 字段信息类
 * @author 范大德
 *
 */
public class FieldInfo {
    //java字段名
    private String pojoFieldName;
    //数据库字段名
    private String dbFieldName;
    private Class<?> type;
    //是否是主键
    private boolean pk = false;
    //update时是否需要更新
    private boolean update = true;
    //insert时是否需要插入
    private boolean insert = true;
    //是否查询
    private boolean query=true;
    //左连接信息
    private LeftInfo left;


    public String getPojoFieldName() {
        return pojoFieldName;
    }
    public void setPojoFieldName(String pojoFieldName) {
        this.pojoFieldName = pojoFieldName;
    }
    public String getDbFieldName() {
        return dbFieldName;
    }
    public FieldInfo setDbFieldName(String dbFieldName) {
        this.dbFieldName = dbFieldName;
        return this;
    }
    public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isInsert() {
		return insert;
	}
	public void setInsert(boolean insert) {
		this.insert = insert;
	}
	public LeftInfo getLeft() {
		return left;
	}
	public void setLeft(LeftInfo left) {
		this.left = left;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}
	public boolean isQuery() {
		return query;
	}


}