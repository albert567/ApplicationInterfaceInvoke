package com.gwamcc.aii.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppDbDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppDbForm;
import com.gwamcc.aii.forms.AppNameForm;
import com.gwamcc.aii.forms.DbNameForm;
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
@Template(key="AppDb.xlsx",value="C100_应用数据库关联模板.xlsx")
public class AppDbDaoImpl extends DefaultDao implements AppDbDao {

	@Override
	public DefaultForm append(AppDbForm app) throws Exception {
		if (save(app)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(AppDbForm app) throws Exception {
		if (saveUpdate(app)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(AppDbForm form)throws Exception {
		if (delete(form)) {
			return MsgFrom.info("删除成功!");
		} else {
			return MsgFrom.err("删除失败!");
		}
	}

	@Override
	public PageDataForm getList(AppDbForm form, PageForm page)throws Exception {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
			if (form.getAppID()>0) {
				cond.param("T_AppDataBase.ApplicationID=:appID");
				map.put("appID", form.getAppID());
			}
			if (!StrKit.isEmpty(form.getDbName())) {
				cond.param("T_DataBase.Name like :dbName");
				map.put("dbName", "%" + form.getDbName() + "%");

			}
			if (!StrKit.isEmpty(form.getDescription())) {
				cond.param(
						"T_DataBase.name like :desc or T_AppDataBase.description like :desc");
				map.put("desc", "%" + form.getDescription() + "%");
			}
		return queryPage(AppDbForm.class, page, new String[] { "id" }, cond.define(), map);
	}


	@Override
	public Object upload(MultipartFile file) throws Exception {
		return this.impExcel(ExcelKit.load(file, 0).title("ApplicationID","DataBaseID")
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
				.addFormatter(1, new DaoValueFormatter(this) {

					private Map<String, Integer>idMap;

					@Override
					public Object format(Object value) {
						return getIdMap().get(value);
					}
					private Map<String, Integer>getIdMap(){
						if(idMap==null){
							idMap=new HashMap<String,Integer>();
							List<DefaultForm>list =getDao().queryList(DbNameForm.class);
							for(DefaultForm form:list){
								if (form instanceof DbNameForm) {
									DbNameForm db = (DbNameForm) form;
									idMap.put(db.getName(), db.getId());
								}

							}
						}
						return idMap;
					}
				})
				, new SimpleDaoRowMapper(this, AppDbForm.class));

	}

	@Override
	public Object download() throws Exception {
		return TemplateDaoImpl.download(this.getClass());
	}


}
