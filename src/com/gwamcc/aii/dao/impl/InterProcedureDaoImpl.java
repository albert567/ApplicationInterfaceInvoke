package com.gwamcc.aii.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.InterProcedureDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.InterProNameForm;
import com.gwamcc.aii.forms.InterProcedureForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

@Repository
@Template(key="InterProcedure.xlsx",value="A200_数据库接口存储过程模板.xlsx")
public class InterProcedureDaoImpl extends DefaultDao implements InterProcedureDao{

	@Override
	public List<DefaultForm> getDbObj(int interID) {
		return queryList(InterProNameForm.class, new ConditionDefBuilder().param("InterfaceID=:InterID").define(),
				new ParamMap().put("InterID", interID));
	}
	@Override
	public List<DefaultForm>getObjList(int interID){
		return queryList(InterProcedureForm.class, new ConditionDefBuilder().param("InterfaceID=:InterID").define(),
				new ParamMap().put("InterID", interID));
	}
	@Override
	public Object getDbObjList(InterProcedureForm interPro, PageForm page) {
		try {
			ConditionDefBuilder builder=new ConditionDefBuilder();
			ParamMap map=new ParamMap();
			if(interPro.getInterID()>0){
				builder.param("T_InterProcedure.InterfaceID = :interID");
				map.put("interID", interPro.getInterID());
			}
			if(!StringUtils.isEmpty(interPro.getDbName())){
				builder.param("T_DataBase.Name like :dbName");
				map.put("dbName", "%" + interPro.getDbName() + "%");
			}
			if(!StringUtils.isEmpty(interPro.getObjName())){
				builder.param("T_DataBaseObj.ObjName like :objName");
				map.put("objName", "%" + interPro.getObjName() + "%");
			}

			return queryPage(InterProcedureForm.class, page, new String[] { "ID" },builder.define(),map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MsgFrom.err("后台处理发生异常!");
	}

	@Override
	public DefaultForm append(InterProcedureForm interPro) {
		if (save(interPro))
			return MsgFrom.info("新增成功!");
		else
			return MsgFrom.err("新增失败!");
	}

	@Override
	public DefaultForm edit(InterProcedureForm interPro) {
		if (saveUpdate(interPro)) {
			return MsgFrom.info("修改成功!");
		} else
			return MsgFrom.err("修改失败!");
	}

	@Override
	public DefaultForm remove(int interProID) {
		if (delete(new InterProNameForm().setId(interProID))) {
			return MsgFrom.info("删除成功!");
		} else
			return MsgFrom.err("删除失败!");
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("AppName","InterfaceID","DbName","ObjID","IsValid")
				.addValidator(new RowValidatorList(0,1,2,3,4).required("数据不合法!"))
				.addFormatter(1, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String key = getCellValue(0)+"."+value;
						return getIdMap().get(key);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(AppInterForm.class);
							for(DefaultForm form:list){
								if (form instanceof AppInterForm) {
									AppInterForm inter = (AppInterForm) form;
									String key = inter.getAppName()+"."+inter.getInterName();
									idMap.put(key, inter.getInterID());
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
				, new SimpleDaoRowMapper(this, InterProcedureForm.class));
	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}


}
