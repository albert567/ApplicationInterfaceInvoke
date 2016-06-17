package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppCodeDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppCodeForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.sql2.Result;
import com.gwamcc.aii.util.template.anno.Template;

/**
 * 应用功能源码操作实现类
 *
 * @author 张亚平
 *
 */
@Repository
@Template(key="CodeStructure.xlsm",value="B110_源代码目录结构.xlsm")
public class AppCodeDaoImpl extends DefaultDao implements AppCodeDao {
	
	@Override
	public List<DefaultForm> getCode(int appID){
		return queryList(AppCodeForm.class,new String[]{"State","T_CodeStructure.Name","T_CodeStructure.ID"},
				new ConditionDefBuilder().param("T_CodeStructure.ApplicationId=:appId").define(),
				new ParamMap().put("appId", appID));
	}
	@Override
	public List<DefaultForm> getRoot(int appID){
		return queryList(AppCodeForm.class,new String[]{"State","T_CodeStructure.Name","T_CodeStructure.ID"},
				new ConditionDefBuilder().param("T_CodeStructure.ApplicationId=:appId And (T_CodeStructure.ParentID is null Or T_CodeStructure.ParentID=0)").define(),
				new ParamMap().put("appId", appID));
	}

	@Override
	public List<DefaultForm> getChild(int codeID){
		return queryList(AppCodeForm.class,new String[]{"State","T_CodeStructure.Name","T_CodeStructure.ID"},
				new ConditionDefBuilder().param("T_CodeStructure.ParentID=:codeId").define(),
				new ParamMap().put("codeId", codeID));
	}
	
	@Override
	public Object getList(AppCodeForm form) throws Exception {
		ParamMap map=new ParamMap();

		ConditionDefBuilder cond=new ConditionDefBuilder();
		if(form.getAppID()>0){
			cond.add("T_CodeStructure.ApplicationID = :appID");
			map.put("appID", form.getAppID());
		}
		if(!StrKit.isEmpty(form.getDescription())){
			cond.add("T_CodeStructure.ID In "
					+ "(SELECT * FROM "
					+"dbo.F_GetCodePath("
					+ "(select convert(varchar,id)+',' "
					+ "FROM T_CodeStructure where name like '%'+:desc+'%' FOR XML PATH(''))))");
			map.put("desc", "%"+form.getDescription()+"%");
		}
		List<DefaultForm> rows= queryList(AppCodeForm.class, new String[]{"State","T_CodeStructure.Name","T_CodeStructure.ID"}, cond.build(),map );
		return new PageDataForm(rows.size(), rows);
	}
	
	@Override
	public Object edit(AppCodeForm form) throws Exception {
		if (saveUpdate(form)) {
			return MsgFrom.info("保存成功!");
		} else{
			return MsgFrom.err("保存失败!");
		}
	}

	@Override
	public Object append(AppCodeForm form) throws Exception {
		if (save(form)) {
			return MsgFrom.info("保存成功!");
		} else{
			return MsgFrom.err("保存失败!");
		}
	}

	@Override
	public Object remove(AppCodeForm form) throws Exception {
		MsgFrom msg = null;
		if ((msg = hasFuncCode(form.getId())) != null) {
			return msg;
		}
		return removeAll(form.getId());
	}
	
	private Object removeAll(int codeID)throws Exception{
		List<DefaultForm> list = new ArrayList<DefaultForm>();
		String sql = "Select ID From F_GetCodeChild(:codeID)";
		Result set = query(sql,new ParamMap().put("codeID", codeID));
		while(set.next()){
			int id = set.get().getInt("ID");
			list.add(new AppCodeForm().setId(id));
		}
		if(delete(list)){
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}
	
	private MsgFrom hasFuncCode(int codeID){
		// 判断是否存在子节点
		String sql = "SELECT count(*) FROM T_AppFuncCode A Where A.CodeID In (Select ID From F_GetCodeChild(?))";
		int count = getJdbcTemplate().queryForObject(sql, new Object[] { codeID }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}

		});
		if (count > 0) {
			return MsgFrom.err("应用功能与该目录有关联,不能删除!");
		}
		return null;
	}

	@Override
	public Object upload(MultipartFile file,final int appID) throws Exception {
		Object obj = this.impExcel(ExcelKit.load(file, 0).title("Name","Path","Pname","ApplicationID")
				.addFormatter(3, new DaoValueFormatter(this) {
					@Override
					public Object format(Object value) {
						return appID;
					}
				})
				.addValidator(new RowValidatorList(0,1,3).required("不是合法值!"))
				, new SimpleDaoRowMapper(this, AppCodeForm.class));
		String sql = "UPDATE T_CodeStructure  SET ParentID = (SELECT B.ID from T_CodeStructure B where B.Path= T_CodeStructure.PName AND B.ApplicationID=?) WHERE ApplicationID=?";
		getJdbcTemplate().update(sql, new Object[]{appID,appID});
		return obj;
	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}
}
