package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.AppFuncCodeDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.AppCodeTreeForm;
import com.gwamcc.aii.forms.AppFuncCodeForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.sql2.Result;

/**
 * 应用功能源码关联操作实现类
 *
 * @author 张亚平
 *
 */
@Repository
public class AppFuncCodeDaoImpl extends DefaultDao implements AppFuncCodeDao {
	@Override
	public DefaultForm save(int funcID, int[] nodes) throws Exception {
		List<DefaultForm> newList = new ArrayList<DefaultForm>();
		for(int i=0;i<nodes.length;i++){
			newList.add(new AppFuncCodeForm(funcID,nodes[i]));
		}
		if(!save(newList)){
			return MsgFrom.err("保存失败!");
		}
		return MsgFrom.info("保存成功!");
	}

	@Override
	public List<DefaultForm> getList(final int appID,final int funcID,int parentID) {
		String sql = "";
		if(parentID<=0){
			sql = "Select ID,Name,ParentID,case when(Select COUNT(*)from T_CodeStructure A where A.ParentID=T_CodeStructure.ID)=0 THEN 'open' ELSE 'closed' END State "
					+ "From T_CodeStructure "
					+ "Where ID Not In (Select CodeID From T_AppFuncCode Where FuncID In (SELECT  ID FROM dbo.F_GetFuncChild("+funcID+"))) " 
					+ "And ApplicationID="+appID+" And (ParentID Is Null Or ParentID=0) Order by State,Name";
		}else{
			sql = "Select ID,Name,ParentID,case when(Select COUNT(*)from T_CodeStructure A where A.ParentID=T_CodeStructure.ID)=0 THEN 'open' ELSE 'closed' END State "
					+ "From T_CodeStructure "
					+ "Where ID Not In (Select CodeID From T_AppFuncCode Where FuncID In (SELECT  ID FROM dbo.F_GetFuncChild("+funcID+"))) " 
					+ "And ApplicationID="+appID+" And ParentID = "+parentID+" Order by State,Name";
		}
		
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		List<DefaultForm> list = jdbcTemplate.query(sql, new RowMapper<DefaultForm>() {
			@Override
			public DefaultForm mapRow(ResultSet rs, int arg1) throws SQLException {
				AppCodeTreeForm lnode = new AppCodeTreeForm();
				lnode.setId(rs.getInt("ID")).setText(rs.getString("Name")).setState(rs.getString("State"));
				return lnode;
			}

		});
		return list;
	}
	
	@Override
	public PageDataForm getCodeList(AppFuncCodeForm form, PageForm page) throws Exception{
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
			if (form.getFuncID()>0) {
				
				String sql = "SELECT ID FROM dbo.F_GetFuncChild(:funcID)";
				Result result = query(sql,new ParamMap().put("funcID", form.getFuncID()));
				List<Integer> ids = new ArrayList<Integer>();
				while(result.next()){
					ids.add(result.getInt("ID"));
				}
				cond.param("T_AppFuncCode.FuncID in (:funcID)");
				map.put("funcID", ids.toArray(new Integer[0]));
			}
			if (!StrKit.isEmpty(form.getCodeName())) {
				cond.param("T_CodeStructure.Name like :codeName");
				map.put("codeName", "%" + form.getCodeName() + "%");

			}
		return queryPage(AppFuncCodeForm.class, page, new String[] { "T_CodeStructure.Name","id" }, cond.define(), map);
	}
	
	@Override
	public DefaultForm remove(AppFuncCodeForm form)throws Exception {
		if (delete(form)) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}
}
