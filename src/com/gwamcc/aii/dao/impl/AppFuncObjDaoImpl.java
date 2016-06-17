package com.gwamcc.aii.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppFuncObjDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppFuncForm;
import com.gwamcc.aii.forms.AppFuncObjForm;
import com.gwamcc.aii.forms.DataBaseObjForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

/**
 * 应用数据库关联操作实现类
 *
 * @author 张亚平
 *
 */
@Repository
@Template(key="AppFuncObj.xlsx",value="C300_应用功能数据库对象关联模板.xlsx")
public class AppFuncObjDaoImpl extends DefaultDao implements AppFuncObjDao {

	@Override
	public DefaultForm append(AppFuncObjForm funcObj) throws Exception {
		if (save(funcObj)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(AppFuncObjForm funcObj) throws Exception {
		if (saveUpdate(funcObj)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(AppFuncObjForm funcObj)throws Exception {
		if (delete(funcObj)) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}

	@Override
	public PageDataForm getList(AppFuncObjForm form, PageForm page)throws Exception {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
			if (form.getFuncID()>0) {
				cond.param("T_AppFuncObj.FuncID=:funcID");
				map.put("funcID", form.getFuncID());
			}
			if (!StrKit.isEmpty(form.getDbName())) {
				cond.param("T_DataBase.Name like :dbName");
				map.put("dbName", "%" + form.getDbName() + "%");

			}
			if (!StrKit.isEmpty(form.getObjName())) {
				cond.param("T_DataBaseObj.ObjName like :objName");
				map.put("objName", "%" + form.getObjName() + "%");

			}
			if (!StrKit.isEmpty(form.getDescription())) {
				cond.param(
						"T_DataBaseObj.ObjName like :description or T_DataBaseObj.ObjDescription like :description");
				map.put("desc", "%" + form.getDescription() + "%");
			}
		return queryPage(AppFuncObjForm.class, page, new String[] { "id" }, cond.define(), map);
	}

	@Override
	public Object upload(MultipartFile file) throws Exception {

		return this.impExcel(ExcelKit.load(file, 0).title("AppName","FuncID","DbName","ObjID")
				.addFormatter(1, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						String keyStr = getCellValue(0)+"."+value;
						return getIdMap().get(keyStr);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(AppFuncForm.class);
							for(DefaultForm form:list){
								if (form instanceof AppFuncForm) {
									AppFuncForm func = (AppFuncForm) form;
									idMap.put(func.getAppName()+"."+func.getName(), func.getId());
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
						String keyStr = getCellValue(2)+"."+value;
						return getIdMap().get(keyStr);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(DataBaseObjForm.class);
							for(DefaultForm form:list){
								if (form instanceof DataBaseObjForm) {
									DataBaseObjForm dbObj = (DataBaseObjForm) form;
									idMap.put(dbObj.getDbName()+"."+dbObj.getObjName(), dbObj.getObjID());
								}

							}
						}
						return idMap;
					}
				})
				, new SimpleDaoRowMapper(this, AppFuncObjForm.class));

	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}
}
