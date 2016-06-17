package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DbObjColDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.FormListValueFormatter;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbColForm;
import com.gwamcc.aii.forms.DbColSrcForm;
import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DbObjColNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SimpleTypeForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

@Repository
@Template(key="DataBaseObjCol.xlsx",value="A110_数据库对象字段模板.xlsx")
public class DbObjColDaoImpl extends DefaultDao implements DbObjColDao{

	@Override
	public List<DefaultForm> getDbCol(int objID) {
		return queryList(DbObjColNameForm.class,new String[]{"ColumnName"},
				new ConditionDefBuilder().param("ObjID=:objID").param("IsValid=:isValid").define(),
				new ParamMap().put("objID", objID).put("isValid", 1));
	}

	@Override
	public List<DefaultForm>getColSrcList(int colID){
		return queryList(DbColSrcForm.class,new String[]{"T_DataBaseObjCol.ColumnName"},
				new ConditionDefBuilder().param("T_ObjColSource.ColID=:colID").define(),
				new ParamMap().put("colID",colID));
	}

	@Override
	public List<DefaultForm> getDbColList(int objID) {
		List<DefaultForm> list = queryList(DbColForm.class,new String[]{"ColumnName"},
				new ConditionDefBuilder().param("ObjID=:objID").param("T_DataBaseObjCol.IsValid=:isValid").define(),
				new ParamMap().put("objID", objID).put("isValid", 1));
		List<Integer>listColId=new ArrayList<Integer>();
		for(DefaultForm form:list){
			if (form instanceof DbColForm) {
				DbColForm colForm = (DbColForm) form;
				listColId.add(colForm.getId());
			}
		}
		if(listColId.size()<=0){
			return list;
		}
		List<DefaultForm>listSrc=queryList(DbColSrcForm.class,new ConditionDef(new Object[][]{
			{"ColID in (:colID)"}
		}),new ParamMap().put("colID", listColId.toArray(new Integer[0])));
		Map<Integer,List<String>>mapSrc=new HashMap<Integer,List<String>>();
		for(DefaultForm form:listSrc){
			if (form instanceof DbColSrcForm) {
				DbColSrcForm formSrc = (DbColSrcForm) form;

				if(!mapSrc.containsKey(formSrc.getColID())){
					mapSrc.put(formSrc.getColID(), new ArrayList<String>());
				}
				List<String>listColSrc=mapSrc.get(formSrc.getColID());
				listColSrc.add(new StringBuffer().append(formSrc.getSrcDbName()).append(".")
					 	.append(formSrc.getSrcObjName()).append(".")
					 	.append(formSrc.getSrcName()).toString());
			}

		}

		for(DefaultForm form:list){
			((DbColForm) form).setSrc(mapSrc.get(((DbColForm) form).getId()));;
		}

		return list;
	}

	@Override
	public Object getDbColList(DbObjColForm dbCol, PageForm page) {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		if(dbCol.getDbID()>0){
			cond.param("T_DataBaseObj.DataBaseID=:dbID");
			map.put("dbID", dbCol.getDbID());
		}
		if(dbCol.getObjID()>0){
			cond.param("T_DataBaseObjCol.ObjID=:objID");
			map.put("objID", dbCol.getObjID());
		}
		if (!StrKit.isEmpty(dbCol.getColumnName())) {
			cond.param("T_DataBaseObjCol.columnName like :colName");
			map.put("colName", "%" + dbCol.getColumnName() + "%");
		}
		if (!StrKit.isEmpty(dbCol.getIsValid())) {
			cond.param("T_DataBaseObj.ISVALID=:isValid");
			map.put("isValid", dbCol.getIsValid());
		}
		if (!StrKit.isEmpty(dbCol.getColumnTypeName())) {
			cond.param("T_Dict.DValue like :colType");
			map.put("colType", "%" + dbCol.getColumnTypeName() + "%");
		}

		if (!StrKit.isEmpty(dbCol.getSrcDesc())) {
			cond.param("T_DataBaseObjCol.ID in (SELECT ColID"
					+ " FROM V_ObjRelation"
					+ " WHERE (srcDbName+'.'+srcObjName+'.'+srcColName) LIKE :srcDesc)");
			map.put("srcDesc", "%" + dbCol.getSrcDesc() + "%");
		}

		if (!StrKit.isEmpty(dbCol.getDescription())) {
			cond.param(
					"T_DataBase.name like :desc or T_DataBaseObj.ObjName like :desc"
				  + " or T_DataBaseObjCol.columnName like :desc or T_Dict.DValue like :desc"
				  + " or T_DataBaseObjCol.Description like :desc");
			map.put("desc", "%" + dbCol.getDescription() + "%");
		}
		try {
			PageDataForm pdfPage= queryPage(DbObjColForm.class, page, new String[] { "ColumnName" }, cond.define(), map);
			List<Integer>listColId=new ArrayList<Integer>();
			for(DefaultForm form:pdfPage.getRows()){
				if (form instanceof DbObjColForm) {
					DbObjColForm colForm = (DbObjColForm) form;
					listColId.add(colForm.getColID());
				}
			}
			if(listColId.size()<=0){
				return pdfPage;
			}
			List<DefaultForm>listSrc=queryList(DbColSrcForm.class,new ConditionDef(new Object[][]{
				{"ColID in (:colID)"}
			}),new ParamMap().put("colID", listColId.toArray(new Integer[0])));
			Map<Integer,List<String>>mapSrc=new HashMap<Integer,List<String>>();
			for(DefaultForm form:listSrc){
				if (form instanceof DbColSrcForm) {
					DbColSrcForm formSrc = (DbColSrcForm) form;

					if(!mapSrc.containsKey(formSrc.getColID())){
						mapSrc.put(formSrc.getColID(), new ArrayList<String>());
					}
					List<String>listColSrc=mapSrc.get(formSrc.getColID());
					listColSrc.add(new StringBuffer().append(formSrc.getSrcDbName()).append(".")
						 	.append(formSrc.getSrcObjName()).append(".")
						 	.append(formSrc.getSrcName()).toString());
				}

			}
			for(DefaultForm form:pdfPage.getRows()){
				((DbObjColForm) form).setSrc(mapSrc.get(((DbObjColForm) form).getColID()));;
			}
			return pdfPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DefaultForm append(DbObjColForm dbCol) {
		if (save(dbCol)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(DbObjColForm dbCol) {
		if (saveUpdate(dbCol)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(int colID) {
		MsgFrom msg = null;
		try {
			if ((msg = hasSubNode(colID)) != null) {
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgFrom.err("后台执行时发生错误!");
		}
		if (delete(new DbObjColNameForm().setId(colID))) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}
	
	private MsgFrom hasSubNode(int colID) throws Exception {
		int count = 0;
		// 判断数据库对象是否存在关联和被关联
		String sql = "select count(*) from T_ObjColSource where ColID=? or SourceID=?";
		count = getJdbcTemplate().queryForObject(sql, new Object[] { colID,colID }, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}

		});
		if (count > 0) {
			return MsgFrom.err("此对象字段已存在字段数据源或被引用,不能删除!");
		}
			
		return null;
	}

	@Override
	public Object colType() {
		return queryList(SimpleTypeForm.class, new ConditionDefBuilder().param("DType=:dtype and IsValid=1").define(),
				new ParamMap().put("dtype", "11"));
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		List<DefaultForm>objList=queryList(DataBaseObjForm.class);

		return this.impExcel(ExcelKit.load(file, 0).title("DbName","ObjID","ColumnName","ColumnType","ColumnLength","Description","IsValid")
				.addFormatter(1, new FormListValueFormatter(objList) {

					@Override
					public Object format(Object value) {
						for(DefaultForm def:getFormList()){
							if (def instanceof DataBaseObjForm) {
								DataBaseObjForm form = (DataBaseObjForm) def;
								if(form.getDbName().equals(getCellValue(0))
										&&form.getObjName().equals(getCellValue(1))){
									return form.getObjID();
								}
							}
						}
						return null;
					}
				})
				.addValidator(new RowValidatorList(0,1,2,3,6).required("不是合法值!"))
				.addValidator(new RowValidatorList(0,1,2).unique())
				.addFormatter(3, new DaoValueFormatter(this) {

					private Map<String, String>idMap;

					@Override
					public Object format(Object value) {
						return getIdMap().get(value);
					}
					private Map<String, String>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,String>();
							List<DefaultForm>list =getDao().queryList(DictForm.class);
							for(DefaultForm form:list){
								if (form instanceof DictForm) {
									DictForm dict = (DictForm) form;
									idMap.put(dict.getDvalue(), dict.getDkey());
								}

							}
						}
						return idMap;
					}
				})
				.addValidator(new RowValidatorList(6).in(0,1))
				, new SimpleDaoRowMapper(this, DbObjColForm.class));
	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}

}
