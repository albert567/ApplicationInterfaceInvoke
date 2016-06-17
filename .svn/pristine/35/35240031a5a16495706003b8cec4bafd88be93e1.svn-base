package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.MethodDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.FormListValueFormatter;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.MethodForm;
import com.gwamcc.aii.forms.MethodNameForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SimpleTypeForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

/**
 * 接口方法数据操作接口实现类
 * @author 张亚平
 *
 */
@Repository
@Template(key="Method.xlsx",value="B210_接口方法模板.xlsx")
public class MethodDaoImpl extends DefaultDao implements MethodDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取方法给下拉框
	 */
	@Override
	public List<DefaultForm> getMethodName(int interfaceID) {
		return queryList(MethodNameForm.class,new String[]{"Name"},
				new ConditionDefBuilder().param("InterfaceID=:interID").define(),
				new ParamMap().put("interID", interfaceID));
	}

	/**
	 * 获取方法列表
	 */
	@Override
	public List<DefaultForm> getMethodList(int interfaceID) {
		return queryList(InterMethodForm.class,new String[]{"T_InterfaceMethod.Name"},
				new ConditionDefBuilder().param("T_InterfaceMethod.InterfaceID=:interID").define(),
				new ParamMap().put("interID", interfaceID));
	}

	/**
	 * 获取方法类型给下拉框
	 */
	@Override
	public List<DefaultForm>getMethodType(){
		return queryList(SimpleTypeForm.class, new String[]{"DKey"},
				new ConditionDefBuilder().param("DType=:dtype and IsValid=1").define(),
				new ParamMap().put("dtype", "03"));
	}

	/**
	 * 根据页面查询条件获取方法列表
	 */
	@Override
	public PageDataForm getMethodList(DefaultForm interMethod,PageForm page){
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();

		if (interMethod != null && interMethod instanceof InterMethodForm) {
			InterMethodForm methodParam = (InterMethodForm) interMethod;
			if(methodParam.getInterID()>0){
				cond.param("T_InterfaceMethod.InterfaceID=:interID");
				map.put("interID", methodParam.getInterID());
			}
			if (!StrKit.isEmpty(methodParam.getMethodName())) {//方法名称
				cond.param("T_InterfaceMethod.Name like :methodName");
				map.put("methodName", "%"+methodParam.getMethodName()+"%");
			}

			if (!StrKit.isEmpty(methodParam.getIsValid())) {//方法状态
				cond.param("T_InterfaceMethod.IsValid = :isValid");
				map.put("isValid",methodParam.getIsValid());
			}

			if (!StrKit.isEmpty(methodParam.getMethodTypeName())) {//方法类型
				cond.param("MeDict.DValue like :methodTypeName");
				map.put("methodTypeName", "%"+methodParam.getMethodTypeName()+"%");
			}


			if (!StrKit.isEmpty(methodParam.getDescription())) {//关键字
				cond.param("T_Application.Name like :description or T_Interface.Name like :description "
						+ "or T_InterfaceMethod.Name like :description or InterDict.DValue like :description "
						+ "or MeDict.DValue like :description "
						+ "or T_InterfaceMethod.Description like :description");
				map.put("description", "%"+methodParam.getDescription()+"%");
			}
		}
		try {
			return queryPage(InterMethodForm.class, page, new String[] { "ID" }, cond.define(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DefaultForm append(InterMethodForm interMethod) {
		interMethod.setCreateEmpID(1)
					.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if (save(interMethod)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(InterMethodForm interMethod) {
		if (saveUpdate(interMethod)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(int methodID) {
		MsgFrom msg = null;
		if ((msg = hasSubNode(methodID)) != null) {
			return msg;
		}
		if (delete(new MethodNameForm().setId(methodID))) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}

	/**
	 * 检测当前方法是否存在子节点
	 * @param applicationID
	 * @return
	 */
	private MsgFrom hasSubNode(int methodID){
		//判断是否存在接口
		String sql="select count(*) from T_InterfaceMethodParameter where InterfaceMethodID=?";
		int count=jdbcTemplate.queryForObject(sql,new Object[]{methodID}, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getInt(1);
			}

		});
		if(count>0){
			return MsgFrom.err("此接口方法存在方法参数,不能删除!<br/>如果此接口方法已失效请使用编辑功能将其置为[失效]!");
		}

		String sql2="select count(*) from T_ApplicationInterfaceInvoke where InterfaceMethodID=?";
		int count2=jdbcTemplate.queryForObject(sql2,new Object[]{methodID}, new RowMapper<Integer>(){
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getInt(1);
			}

		});
		if(count2>0){
			return MsgFrom.err("此接口方法被其他应用系统调用,不能删除!<br/>如果此接口方法已失效请使用编辑功能将其置为[失效]!");
		}

		return null;
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		List<DefaultForm> interList = queryList(AppInterForm.class);

		return this.impExcel(ExcelKit.load(file, 0).title("AppName","InterfaceID","Name","RW",
				"MethodTypeID","Description","Sn","IsValid","CreateEmpID","CreateDate")
				.addValidator(new RowValidatorList(0,1,2).unique())
				.addFormatter(1, new FormListValueFormatter(interList) {//格式化接口ID

					@Override
					public Object format(Object value) {
						for(DefaultForm def:getFormList()){
							if (def instanceof AppInterForm) {
								AppInterForm form = (AppInterForm) def;
								if(form.getAppName().equals(getCellValue(0))
										&&form.getInterName().equals(getCellValue(1))){
									return form.getInterID();
								}
							}
						}
						return null;
					}
				})
				.addValidator(new RowValidatorList(3).in(0,1))
				.addFormatter(4, new DaoValueFormatter(this) {

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
									if("03".equals(dict.getDtype()))
										idMap.put(dict.getDvalue(), dict.getDkey());
								}

							}
						}
						return idMap;
					}
				})
				.addValidator(new RowValidatorList(7).in(0,1))
				.addFormatter(9, new DaoValueFormatter(this) {
					@Override
					public Object format(Object value) {
						// TODO Auto-generated method stub
						return new SimpleDateFormat("yyyy-MM-dd").format(value);
					}})
				, new SimpleDaoRowMapper(this, MethodForm.class));
	}


	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}


}
