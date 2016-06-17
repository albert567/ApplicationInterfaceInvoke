package com.gwamcc.aii.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DbObjRelDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbObjRelForm;
import com.gwamcc.aii.forms.DbObjRelNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

@Repository
@Template(key="ObjRelation.xlsx",value="A300_数据库对象关联模板.xlsx")
public class DbObjRelDaoImpl extends DefaultDao implements DbObjRelDao{

	@Override
	public List<DefaultForm> getDbObj(int objID) {
		return queryList(DbObjRelNameForm.class,new String[]{"T_DataBaseObj.ObjName"},
				new ConditionDefBuilder().param("ParentID=:objID").define(),
				new ParamMap().put("objID", objID));
	}

	@Override
	public Object getDbObjList(DbObjRelForm objRel, PageForm page) {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		if(objRel.getParentID()>0){
			cond.param("ParentID = :parentID");
			map.put("parentID", objRel.getParentID());
		}
		if(!StrKit.isEmpty(objRel.getChildName())){
			cond.param("Child.ObjName like :childName");
			map.put("childName", "%" + objRel.getChildName() + "%");
		}
		if(!StrKit.isEmpty(objRel.getChildTypeName())){
			cond.param("ChildType.Dvalue like :childTypeName");
			map.put("childTypeName", "%"+objRel.getChildTypeName()+"%");
		}
		if (!StrKit.isEmpty(objRel.getIsValid())) {
			cond.param("T_ObjRelation.ISVALID=:isValid");
			map.put("isValid", objRel.getIsValid());
		}

		try {
			return queryPage(DbObjRelForm.class, page, new String[] { "id" }, cond.define(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public DefaultForm append(DbObjRelForm objRel) {
		if (save(objRel)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(DbObjRelForm objRel) {
		if (saveUpdate(objRel)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(int relID) {
		if (delete(new DbObjRelForm().setId(relID))) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("PrtDbName","ParentID","ChldDbName","ChildID","IsValid")
				.addValidator(new RowValidatorList(0,1,2,3).required("数据不合法!"))
				.addFormatter(1, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String key = (getCellValue(0)+"."+value).toUpperCase();
						return getIdMap().get(key);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(DataBaseObjForm.class);
							for(DefaultForm form:list){
								if (form instanceof DataBaseObjForm) {
									DataBaseObjForm obj = (DataBaseObjForm) form;
									String key = (obj.getDbName()+"."+obj.getObjName()).toUpperCase();
									idMap.put(key, obj.getObjID());
								}
							}
						}
						return idMap;
					}
				})
				.addFormatter(3, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String key = (getCellValue(2)+"."+value).toUpperCase();
						return getIdMap().get(key);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(DataBaseObjForm.class);
							for(DefaultForm form:list){
								if (form instanceof DataBaseObjForm) {
									DataBaseObjForm obj = (DataBaseObjForm) form;
									String key = (obj.getDbName()+"."+obj.getObjName()).toUpperCase();
									idMap.put(key, obj.getObjID());
								}
							}
						}
						return idMap;
					}
				})
				.addValidator(new RowValidatorList(4).in(0,1))
				, new SimpleDaoRowMapper(this, DbObjRelForm.class));
	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}



}
