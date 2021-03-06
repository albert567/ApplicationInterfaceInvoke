package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppFuncDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.AppNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SimpleFuncForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

/**
 * 应用功能操作实现类
 *
 * @author 范大德
 *
 */
@Repository
@Template(key="ApplicationFunction.xlsx",value="B100_应用功能模板.xlsx")
public class AppFuncDaoImpl extends DefaultDao implements AppFuncDao {
	@Override
	public DefaultForm append(AppFuncForm app) throws Exception {
		if (save(app)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(AppFuncForm app) throws Exception {
		if (saveUpdate(app)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(AppFuncForm form)throws Exception {
		MsgFrom msg = null;
		if ((msg = hasSubNode(form.getId())) != null) {
			return msg;
		}
		if (delete(form)) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}
	
	/**
	 * 检测当前应用程序是否存在子节点
	 *
	 * @param applicationID
	 * @return
	 */
	private MsgFrom hasSubNode(int funcID) {
		// 判断是否存在关联
		MsgFrom msg = null;
		
		if((msg=hasChild(funcID))!=null||(msg=hasFuncObj(funcID))!=null
				||(msg=hasCode(funcID))!=null||(msg=hasAttach(funcID))!=null)
			return msg;
		return null;
	}
	//是否有关联对象
	private MsgFrom hasFuncObj(int funcID){
		String sql = "select count(*) from T_AppFuncObj where FuncID=?";
		int count = getJdbcTemplate().queryForObject(sql, new Object[] { funcID }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}

		});
		if (count > 0) {
			return MsgFrom.err("此应用功能存在数据库对象,不能删除!");
		}
		return null;
	}
	//是否有子功能
	private MsgFrom hasChild(int funcID){
		String sql = "select count(*) from T_ApplicationFunction where ParentID=?";
		int count = getJdbcTemplate().queryForObject(sql, new Object[] { funcID }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}

		});
		if (count > 0) {
			return MsgFrom.err("此应用功能存在子功能,不能删除!");
		}
		return null;
	}
	//是否存在源代码
	private MsgFrom hasCode(int funcID){
		String sql = "select count(*) from T_AppFuncCode where FuncID=?";
		int count = getJdbcTemplate().queryForObject(sql, new Object[] { funcID }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}

		});
		if (count > 0) {
			return MsgFrom.err("此应用功能存在源代码,不能删除!");
		}
		return null;
	}
	//是否存在文档关联
	private MsgFrom hasAttach(int funcID){
		String sql = "select count(*) from T_Attachment where ParentID=? And Type='function'";
		int count = getJdbcTemplate().queryForObject(sql, new Object[] { funcID }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}

		});
		if (count > 0) {
			return MsgFrom.err("此应用功能存在文档关联,不能删除!");
		}
		return null;
	}

	@Override
	public PageDataForm getList(AppFuncForm form, PageForm page)throws Exception {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		cond.param("T_Application.IsValid=:appValid");
		map.put("appValid", 1);
		if (form != null && form instanceof AppFuncForm) {
			AppFuncForm appParam = (AppFuncForm) form;
			if (appParam.getAppID()>0) {
				cond.param("T_Application.ID = :appID");
				map.put("appID", appParam.getAppID());

			}
			cond.param("T_ApplicationFunction.ParentID = :parentID");
			map.put("parentID", appParam.getParentID());
			if (!StrKit.isEmpty(appParam.getName())) {
				cond.param("T_ApplicationFunction.name like :name");

				map.put("name", "%" + appParam.getName() + "%");
			}
			if (appParam.getIsValid() >= 0) {
				cond.param("T_ApplicationFunction.ISVALID=:valid");
				map.put("valid", appParam.getIsValid());
			}
			/*if (!StrKit.isEmpty(appParam.getParentName())) {
				cond.param("ParentFunc.Name like :parentName");

				map.put("parentName", "%" + appParam.getParentName() + "%");
			}*/
			if (!StrKit.isEmpty(appParam.getDescription())) {
				cond.param(
						"T_ApplicationFunction.name like :desc or T_Application.name like :desc or T_ApplicationFunction.Description like :desc");
				map.put("desc", "%" + appParam.getDescription() + "%");
			}
		}
		return queryPage(AppFuncForm.class, page, new String[] { "sn","id" }, cond.define(), map);
	}

	@Override
	public List<DefaultForm> getFuncList(int applicationID)throws Exception {
		return null;

	}

	@Override
	public List<DefaultForm> getName(int applicationID)throws Exception {
		return queryList(SimpleFuncForm.class,new String[]{"sn","id"},
				new ConditionDefBuilder().param("ApplicationId=:appId").param("T_ApplicationFunction.IsValid=:valid").define(),
				new ParamMap().put("appId", applicationID).put("valid", 1));
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("ApplicationID","NAME","description","IsValid")
				.addFormatter(0, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						return getIdMap().get(value);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(AppNameForm.class);
							for(DefaultForm form:list){
								if (form instanceof AppNameForm) {
									AppNameForm app = (AppNameForm) form;
									idMap.put(app.getName(), app.getId());
								}

							}
						}
						return idMap;
					}
				})
				.addValidator(new RowValidatorList(0).required("不是合法值!"))
				.addValidator(new RowValidatorList(1).unique())
				.addValidator(new RowValidatorList(3).in(0,1))
				, new SimpleDaoRowMapper(this, AppFuncForm.class));

	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}

}
