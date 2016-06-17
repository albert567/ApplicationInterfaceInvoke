package com.gwamcc.aii.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.MethodParamDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.MethodParamForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.ParamForm;
import com.gwamcc.aii.forms.ParamNameForm;
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
@Template(key="MethodParam.xlsx",value="B211_方法参数模板.xlsx")
public class MethodParamDaoImpl extends DefaultDao implements MethodParamDao {
	/**
	 * 根据页面查询条件获取方法列表
	 */
	@Override
	public PageDataForm getParamList(DefaultForm methodParam,PageForm page){
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();

		if (methodParam != null && methodParam instanceof MethodParamForm) {
			MethodParamForm param = (MethodParamForm) methodParam;

			if(param.getMethodID()>0){
				cond.param("InterfaceMethodID = :methodID");
				map.put("methodID", param.getMethodID());
			}

			if (!StrKit.isEmpty(param.getParamName())) {//方法名称
				cond.param("T_InterfaceMethodParameter.FieldName like :paramName");
				map.put("paramName", "%"+param.getParamName()+"%");
			}
			if (!StrKit.isEmpty(param.getParamTypeName())) {//参数类型
				cond.param("ParamType.DValue like :paramTypeName");
				map.put("paramTypeName", "%"+param.getParamTypeName()+"%");
			}
			if (!StrKit.isEmpty(param.getInOrOut())) {//入参出参
				cond.param("InOrOut = :inOrOut");
				map.put("inOrOut", param.getInOrOut());
			}

			if (!StrKit.isEmpty(param.getIsValid())) {//参数状态
				cond.param("T_InterfaceMethodParameter.IsValid = :isValid");
				map.put("isValid", param.getIsValid());
			}

			if (!StrKit.isEmpty(param.getDescription())) {//关键字
				cond.param("T_Application.Name like :description "
						+ "or T_Interface.Name like :description "
						+ "or T_InterfaceMethod.Name like :description "
						+ "or ParamType.DValue like :description "
						+ "or FieldName like :description");
				map.put("description", "%"+param.getDescription()+"%");
			}
		}
		try {
			return queryPage(MethodParamForm.class, page, new String[] { "FieldName" }, cond.define(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取方法参数类型给下拉框
	 */
	@Override
	public List<DefaultForm> getParamType() {
		String[] types = new String[]{"10","11","12"};
		return queryList(SimpleTypeForm.class, new String[]{"DValue"},
				new ConditionDefBuilder().param("DType in (:dtype) and IsValid=1").define(),
				new ParamMap().put("dtype",types));
	}

	/**
	 * 获取方法参数列表
	 */
	@Override
	public List<DefaultForm> getParamList(int methodID) {
		return queryList(ParamForm.class, new String[]{"FieldName"},
				new ConditionDefBuilder().param("InterfaceMethodID=:methodID").define(),
				new ParamMap().put("methodID",methodID));
	}

	/**
	 * 获取参数名称列表给下拉框
	 */
	@Override
	public List<DefaultForm> getParamName(int methodID) {
		return queryList(ParamNameForm.class, new String[]{"FieldName"},
				new ConditionDefBuilder().param("InterfaceMethodID=:methodID").define(),
				new ParamMap().put("methodID",methodID));
	}

	@Override
	public DefaultForm edit(MethodParamForm methodParam) {
		if (saveUpdate(methodParam)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm append(MethodParamForm methodParam) {
		if (save(methodParam)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm remove(int paramID) {
		if (delete(new ParamNameForm().setId(paramID))) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("AppName","InterName","InterfaceMethodID","FieldName","FieldTypeID",
				"FieldLength","InOrOut","Description","Sn","IsValid")
				.addValidator(new RowValidatorList(0,1,2,3).unique())
				.addFormatter(2, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String key = getCellValue(0)+"."+getCellValue(1)+"."+value;
						return getIdMap().get(key);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(InterMethodForm.class);
							for(DefaultForm form:list){
								if (form instanceof InterMethodForm) {
									InterMethodForm method = (InterMethodForm) form;
									String key = method.getAppName()+"."+method.getInterName()+"."+method.getMethodName();
									idMap.put(key,method.getMethodID());
								}
							}
						}
						return idMap;
					}
				})
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
									if("10".equals(dict.getDtype())||"11".equals(dict.getDtype())||"12".equals(dict.getDtype()))
										idMap.put(dict.getDvalue(), dict.getDkey());
								}
							}
						}
						return idMap;
					}
				})
				.addValidator(new RowValidatorList(6).in(0,1,2))
				.addValidator(new RowValidatorList(9).in(0,1))
				, new SimpleDaoRowMapper(this, ParamForm.class));

	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}


}
