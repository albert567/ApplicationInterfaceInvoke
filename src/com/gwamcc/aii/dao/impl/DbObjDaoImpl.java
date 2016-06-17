package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.DbObjDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DbNameForm;
import com.gwamcc.aii.forms.DbObjColForm;
import com.gwamcc.aii.forms.DbObjForm;
import com.gwamcc.aii.forms.DbObjNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SimpleTypeForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.pdm.PDMParser;
import com.gwamcc.aii.util.pdm.bean.PDM;
import com.gwamcc.aii.util.pdm.bean.PDMColumn;
import com.gwamcc.aii.util.pdm.bean.PDMTable;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

@Repository
@Template(key = "DataBaseObj.xlsx", value = "A100_数据库对象模板.xlsx")
public class DbObjDaoImpl extends DefaultDao implements DbObjDao {

	@Override
	public List<DefaultForm> getDbObj(int dbID) {
		return queryList(DbObjNameForm.class, new String[] { "ObjName" },
				new ConditionDefBuilder().param("DataBaseID=:dbID").param("IsValid=:isValid").define(),
				new ParamMap().put("dbID", dbID).put("isValid", 1));
	}

	@Override
	public Object objType() {
		return queryList(SimpleTypeForm.class, new String[] { "DKey" },
				new ConditionDefBuilder().param("DType=:dtype and IsValid=1").define(),
				new ParamMap().put("dtype", "04"));
	}

	@Override
	public List<DefaultForm> getDbObjList(int dbID) {
		return queryList(DbObjForm.class, new String[] { "ObjName" },
				new ConditionDefBuilder().param("DataBaseID=:dbID").param("T_DataBaseObj.IsValid=:isValid").define(),
				new ParamMap().put("dbID", dbID).put("isValid", 1));
	}

	@Override
	public Object getDbObjList(DataBaseObjForm dbObj, PageForm page) {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		if (dbObj.getDbID() > 0) {
			cond.param("T_DataBaseObj.DataBaseID=:dbID");
			map.put("dbID", dbObj.getDbID());
		}
		if (!StrKit.isEmpty(dbObj.getObjName())) {
			cond.param("T_DataBaseObj.ObjName like :objName");
			map.put("objName", "%" + dbObj.getObjName() + "%");
		}
		if (!StrKit.isEmpty(dbObj.getIsValid())) {
			cond.param("T_DataBaseObj.ISVALID=:isValid");
			map.put("isValid", dbObj.getIsValid());
		}
		if (!StrKit.isEmpty(dbObj.getObjTypeName())) {
			cond.param("T_Dict.DValue like :objType");
			map.put("objType", "%" + dbObj.getObjTypeName() + "%");
		}

		if (!StrKit.isEmpty(dbObj.getDescription())) {
			cond.param(
					"T_DataBase.name like :desc or T_DataBaseObj.ObjName like :desc or T_Dict.DValue like :desc or T_DataBaseObj.ObjDescription like :desc");
			map.put("desc", "%" + dbObj.getDescription() + "%");
		}
		try {
			return queryPage(DataBaseObjForm.class, page, new String[] { "ObjName" }, cond.define(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DefaultForm append(DataBaseObjForm dbObj) {
		dbObj.setSn(1);
		if (save(dbObj)) {
			return MsgFrom.info("新增成功!");
		} else {
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(DataBaseObjForm dbObj) {
		if (saveUpdate(dbObj)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(int objID) {
		MsgFrom msg = null;
		try {
			if ((msg = hasSubNode(objID)) != null) {
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgFrom.err("后台执行时发生错误!");
		}
		if (delete(new DbObjNameForm().setId(objID))) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}
	
	private MsgFrom hasSubNode(int objID) throws Exception {
		List<DefaultForm> list = queryList(DataBaseObjForm.class, new ConditionDefBuilder().param("T_DataBaseObj.ID=:objID").define(),
				new ParamMap().put("objID", objID));
		DataBaseObjForm dbObj = null;
		int count = 0;
		if(list.isEmpty()){
			return MsgFrom.err("找不到数据库对象!");
		}else{
			dbObj = (DataBaseObjForm)list.get(0);
			if("0402".equals(dbObj.getObjTypeID())||"0403".equals(dbObj.getObjTypeID())){
				// 判断表或视图是否存在字段信息
				String sql = "select count(*) from T_DataBaseObjCol where ObjID=?";
				count = getJdbcTemplate().queryForObject(sql, new Object[] { objID }, new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
						return rs.getInt(1);
					}

				});
				if (count > 0) {
					return MsgFrom.err("此数据库对象已存在字段信息,不能删除!");
				}
			}else if("0401".equals(dbObj.getObjTypeID())||"0404".equals(dbObj.getObjTypeID())){
				// 判断存储过程或函数是否存在参数信息
				String sql = "select count(*) from T_ObjParam where ObjID=?";
				count = getJdbcTemplate().queryForObject(sql, new Object[] { objID }, new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
						return rs.getInt(1);
					}

				});
				if (count > 0) {
					return MsgFrom.err("此数据库对象已存在参数信息,不能删除!");
				}
			}
			
			// 判断数据库对象是否存在关联和被关联
			String sql = "select count(*) from T_ObjRelation where ParentID=? or ChildID=?";
			count = getJdbcTemplate().queryForObject(sql, new Object[] { objID,objID }, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getInt(1);
				}

			});
			if (count > 0) {
				return MsgFrom.err("此数据库对象已关联对象或被对象关联,不能删除!");
			}
			
			return null;
		}
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("DataBaseID", "ObjName", "ObjType", "ObjDescription", "IsValid")
				.addFormatter(0, new DaoValueFormatter(this) {

					private Map<String, Integer> idMap;

					@Override
					public Object format(Object value) {
						return getIdMap().get(value);
					}

					private Map<String, Integer> getIdMap() {
						if (idMap == null) {
							idMap = new HashMap<String, Integer>();
							List<DefaultForm> list = getDao().queryList(DbNameForm.class);
							for (DefaultForm form : list) {
								if (form instanceof DbNameForm) {
									DbNameForm db = (DbNameForm) form;
									idMap.put(db.getName(), db.getId());
								}

							}
						}
						return idMap;
					}
				}).addValidator(new RowValidatorList(1).unique()).addFormatter(2, new DaoValueFormatter(this) {

					private Map<String, String> idMap;

					@Override
					public Object format(Object value) {
						return getIdMap().get(value);
					}

					private Map<String, String> getIdMap() {
						if (idMap == null) {
							idMap = new HashMap<String, String>();
							List<DefaultForm> list = getDao().queryList(DictForm.class);
							for (DefaultForm form : list) {
								if (form instanceof DictForm) {
									DictForm dict = (DictForm) form;
									if ("04".equals(dict.getDtype()))
										idMap.put(dict.getDvalue(), dict.getDkey());
								}

							}
						}
						return idMap;
					}
				}).addValidator(new RowValidatorList(4).in(0, 1)), new SimpleDaoRowMapper(this, DataBaseObjForm.class));
	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}

	@Override
	public List<DefaultForm> getProcedure(int dbID) {
		return queryList(
				DbObjNameForm.class, new ConditionDefBuilder().param("DataBaseID=:dbID").param("IsValid=:isValid")
						.param("ObjType=:type").define(),
				new ParamMap().put("dbID", dbID).put("isValid", 1).put("type", "0401"));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object uploadPdm(int db, MultipartFile file) throws Exception {
		DbNameForm dbName = (DbNameForm) queryEntity(DbNameForm.class,
				new ConditionDef(new Object[][] { { "id=:id" } }), new ParamMap().put("id", db));

		String loguid = UUID.randomUUID().toString();
		// 取数据库字段类型字典
		List<DefaultForm> list = queryList(SimpleTypeForm.class, new ConditionDef(new Object[][] { { "DTYPE=:type" } }),
				new ParamMap().put("type", "11"));
		Map<String, String> map = new HashMap<String, String>();
		for (DefaultForm form : list) {
			SimpleTypeForm type = (SimpleTypeForm) form;
			map.put(type.getName().toLowerCase(), type.getId());
		}

		PDM pdm = PDMParser.parsePDM(file.getInputStream());
		List<PDMTable> tables = pdm.getTables();
		for (PDMTable table : tables) {
			 String tUid=UUID.randomUUID().toString();
			// 处理表
			DataBaseObjForm form = new DataBaseObjForm();
			form.setObjTypeID("0402");
			form.setObjName(table.getUser().getCode()+ "."+table.getCode());
			form.setDescription(table.getName());
			form.setUuid(tUid);
			form.setDbID(dbName.getId());
			form.setDbName(dbName.getName());
			form.setIsValid("1");
			form.set_log(loguid);
			save(form);
			DataBaseObjForm obj = (DataBaseObjForm) queryEntity(DataBaseObjForm.class,
					new ConditionDef(new Object[][] { { "T_DataBaseObj.uuid=:uid" } }),
					new ParamMap().put("uid", tUid));
			// 处理表字段
			for (PDMColumn col : table.getColumns()) {
				DbObjColForm cForm = new DbObjColForm();
				cForm.setColumnName(col.getCode());
				cForm.setDescription(col.getName());
				cForm.setColumnTypeID(
						map.get(col.getDataType().toLowerCase().substring(0, col.getDataType().indexOf("(") > 0
								? col.getDataType().indexOf("(") : col.getDataType().length())));
				cForm.setLength(col.getLength());
				cForm.setIsValid("1");
				cForm.setObjID(obj.getObjID());
				cForm.setDbID(obj.getDbID());
				cForm.set_log(loguid);
				save(cForm);
			}
		}
		MsgFrom retMsg = MsgFrom.info("success");
		retMsg.setUuid(loguid);
		return retMsg;
	}

}
