package com.gwamcc.aii.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.InterInvokeDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterInvokeForm;
import com.gwamcc.aii.forms.InterMethodForm;
import com.gwamcc.aii.forms.InvokeForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

/**
 * 应用接口调用数据操作接口实现类
 * @author 张亚平
 *
 */
@Repository
@Template(key="InterfaceInvoke.xlsx",value="B220_接口方法调用模板.xlsx")
public class InterInvokeDaoImpl extends DefaultDao implements InterInvokeDao {

	@Override
	public PageDataForm getInterInvokeList(DefaultForm interInvoke,PageForm page){
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		if (interInvoke != null && interInvoke instanceof InterInvokeForm) {
			InterInvokeForm interInvokeParam = (InterInvokeForm) interInvoke;
			if(interInvokeParam.getMethodID()>0){
				cond.param("InterfaceMethodID = :MethodID");
				map.put("MethodID",interInvokeParam.getMethodID());

				if (!StrKit.isEmpty(interInvokeParam.getInvokeAppName())) {//应用名称
					cond.param("T_InvokeApp.name like :AppName");
					map.put("AppName", "%" + interInvokeParam.getInvokeAppName() + "%");
				}
				if (!StrKit.isEmpty(interInvokeParam.getInvokeInterName())) {//接口名称
					cond.param("T_InvokeInter.name like :InterName");
					map.put("InterName", "%" + interInvokeParam.getInvokeInterName() + "%");
				}

				if (!StrKit.isEmpty(interInvokeParam.getInvokeMeName())) {//方法名称
					cond.param("T_InvokeMe.name like :MethodName");
					map.put("MethodName", "%" + interInvokeParam.getInvokeMeName() + "%");
				}

				if (!StrKit.isEmpty(interInvokeParam.getDescription())) {//关键字
					cond.param(
							"T_InvokeApp.name like :Description or T_InvokeInter.name like :Description or T_InvokeMe.name like :Description or T_ApplicationInterfaceInvoke.Description like :Description");
					map.put("Description", "%" + interInvokeParam.getDescription() + "%");
				}
			}
			if(interInvokeParam.getInvokeMethodID()>0){
				cond.param("InvokeMethodID = :MethodID");
				map.put("MethodID",interInvokeParam.getInvokeMethodID());

				if (!StrKit.isEmpty(interInvokeParam.getAppName())) {//应用名称
					cond.param("T_App.Name like :AppName");
					map.put("AppName", "%" + interInvokeParam.getAppName() + "%");
				}
				if (!StrKit.isEmpty(interInvokeParam.getInterName())) {//接口名称
					cond.param("T_Inter.name like :InterName");
					map.put("InterName", "%" + interInvokeParam.getInterName() + "%");
				}

				if (!StrKit.isEmpty(interInvokeParam.getMethodName())) {//方法名称
					cond.param("T_Method.name like :MethodName");
					map.put("MethodName", "%" + interInvokeParam.getMethodName() + "%");
				}

				if (!StrKit.isEmpty(interInvokeParam.getDescription())) {//关键字
					cond.param(
							"T_App.Name like :Description or T_Inter.name like :Description or T_Method.name like :Description or T_ApplicationInterfaceInvoke.Description like :Description");
					map.put("Description", "%" + interInvokeParam.getDescription() + "%");
				}
			}

		}
		try {
			return queryPage(InterInvokeForm.class, page, new String[] { "id" }, cond.define(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public DefaultForm edit(InterInvokeForm interInvoke) {
		if (saveUpdate(interInvoke)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}


	@Override
	public DefaultForm append(InterInvokeForm interInvoke) {
		interInvoke.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		interInvoke.setCreateEmpID(1);
		if (save(interInvoke)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}


	@Override
	public DefaultForm remove(int interInvokeID) {
		if (delete(new InvokeForm().setInterInvokeID(interInvokeID))) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}


	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("AppName","InterfaceID",
				"InterfaceMethodID","InvokeAppName","InvokeInterName",
				"InvokeMethodID","IsValid",
				"CreateEmpID","CreateDate")
				.addValidator(new RowValidatorList(0,1,2,3,4,5,6,7,8).required("数据不合法!"))
				.addValidator(new RowValidatorList(0,1,2,3,4,5).unique())
				.addFormatter(1, new DaoValueFormatter(this) {
					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String interKey = getCellValue(0)+"."+value;
						System.out.println(interKey+"=="+getIdMap().get(interKey));
						return getIdMap().get(interKey);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(AppInterForm.class);
							for(DefaultForm form:list){
								if (form instanceof AppInterForm) {
									AppInterForm inter = (AppInterForm) form;
									String interKey = inter.getAppName()+"."+inter.getInterName();
									idMap.put(interKey, inter.getInterID());
								}

							}
						}
						return idMap;
					}
				})
				.addFormatter(2, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String methodKey = getCellValue(0)+"."+getCellValue(1)+"."+value;
						System.out.println(methodKey+"=="+getIdMap().get(methodKey));
						return getIdMap().get(methodKey);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(InterMethodForm.class);
							for(DefaultForm form:list){
								if (form instanceof InterMethodForm) {
									InterMethodForm method = (InterMethodForm) form;
									String methodKey = method.getAppName()+"."+method.getInterName()+"."+method.getMethodName();
									idMap.put(methodKey, method.getMethodID());
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
						String methodKey = getCellValue(3)+"."+getCellValue(4)+"."+value;
						System.out.println(methodKey+"=="+getIdMap().get(methodKey));
						return getIdMap().get(methodKey);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(InterMethodForm.class);
							for(DefaultForm form:list){
								if (form instanceof InterMethodForm) {
									InterMethodForm method = (InterMethodForm) form;
									String methodKey = method.getAppName()+"."+method.getInterName()+"."+method.getMethodName();
									idMap.put(methodKey, method.getMethodID());
								}

							}
						}
						return idMap;
					}
				})
				.addValidator(new RowValidatorList(6).in(0,1))
				.addFormatter(8, new DaoValueFormatter(this) {
					@Override
					public Object format(Object value) {
						// TODO Auto-generated method stub
						return new SimpleDateFormat("yyyy-MM-dd").format(value);
					}
				}), new SimpleDaoRowMapper(this, InterInvokeForm.class));
	}


	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}


}
