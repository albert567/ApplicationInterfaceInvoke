package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DbColSrcDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.ColSrcForm;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbColSrcForm;
import com.gwamcc.aii.forms.DbColSrcNameForm;
import com.gwamcc.aii.forms.DbObjColForm;
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
@Template(key="ColumnSource.xlsx",value="A111_字段数据源模板.xlsx")
public class DbColSrcDaoImpl extends DefaultDao implements DbColSrcDao{

	@Override
	public List<DefaultForm> getColSrc(int colID) {
		return queryList(DbColSrcNameForm.class,
				new ConditionDefBuilder().param("ColID=:colID").define(),
				new ParamMap().put("colID", colID));
	}

	@Override
	public Object getColSrcList(DbColSrcForm colSrc, PageForm page) {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		if(colSrc.getColID()>0){
			cond.param("ColID = :colID");
			map.put("colID", colSrc.getColID());
		}
		if(!StrKit.isEmpty(colSrc.getSrcDbName())){
			cond.param("T_DataBase.Name like :srcDbName");
			map.put("srcDbName", "%" + colSrc.getSrcDbName() + "%");
		}
		if(!StrKit.isEmpty(colSrc.getSrcObjName())){
			cond.param("T_DataBaseObj.ObjName like :srcObjName");
			map.put("srcObjName", "%" + colSrc.getSrcObjName() + "%");
		}
		if(!StrKit.isEmpty(colSrc.getSrcName())){
			cond.param("T_DataBaseObjCol.ColumnName like :srcName");
			map.put("srcName", "%" + colSrc.getSrcName() + "%");
		}
		if(!StrKit.isEmpty(colSrc.getSrcTypeName())){
			cond.param("T_Dict.DValue like :srcTypeName");
			map.put("srcTypeName", "%"+colSrc.getSrcTypeName()+"%");
		}
		if (!StrKit.isEmpty(colSrc.getIsValid())) {
			cond.param("T_ObjColSource.IsValid=:isValid");
			map.put("isValid", colSrc.getIsValid());
		}
		try {
			return queryPage(DbColSrcForm.class, page, new String[] { "id" }, cond.define(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DefaultForm append(DbColSrcForm colSrc) {
		if (save(colSrc)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(DbColSrcForm colSrc) {
		if (saveUpdate(colSrc)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(int id) {
		if (delete(new DbColSrcForm().setId(id))) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("SrcDbID","SrcObjID","SourceID","DbID","ObjID","ColID","IsValid")
				.addValidator(new RowValidatorList(0,1,2,3,4,5).required("数据不合法!"))
				.addFormatter(2, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String colKey = (getCellValue(0)+"."+getCellValue(1)+"."+value).toUpperCase();
						return getIdMap().get(colKey);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(DbObjColForm.class);
							for(DefaultForm form:list){
								if (form instanceof DbObjColForm) {
									DbObjColForm col = (DbObjColForm) form;
									String colKey = (col.getDbName()+"."+col.getObjName()+"."+col.getColumnName()).toUpperCase();
									idMap.put(colKey, col.getColID());
								}
							}
						}
						return idMap;
					}
				})
				.addFormatter(5, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String colKey = (getCellValue(3)+"."+getCellValue(4)+"."+value).toUpperCase();
						return getIdMap().get(colKey);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(DbObjColForm.class);
							for(DefaultForm form:list){
								if (form instanceof DbObjColForm) {
									DbObjColForm col = (DbObjColForm) form;
									String colKey = (col.getDbName()+"."+col.getObjName()+"."+col.getColumnName()).toUpperCase();
									idMap.put(colKey, col.getColID());
								}
							}
						}
						return idMap;
					}
				})
				.addValidator(new RowValidatorList(6).in(0,1))
				, new SimpleDaoRowMapper(this, ColSrcForm.class));
	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}
}
