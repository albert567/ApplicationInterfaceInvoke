package com.gwamcc.aii.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gwamcc.aii.dao.AppInterDao;
import com.gwamcc.aii.dao.core.DaoValueFormatter;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.dao.core.SimpleDaoRowMapper;
import com.gwamcc.aii.forms.AppInterForm;
import com.gwamcc.aii.forms.AppNameForm;
import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.DictForm;
import com.gwamcc.aii.forms.InterForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.SimpleInterForm;
import com.gwamcc.aii.forms.SimpleTypeForm;
import com.gwamcc.aii.util.ExcelKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.excel.RowValidatorList;
import com.gwamcc.aii.util.sql2.ConditionDefBuilder;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.template.anno.Template;

/**
 * 应用接口操作实现类
 *
 * @author 范大德
 *
 */
@Repository
@Template(key="AppInter.xlsx",value="B200_应用接口模板.xlsx")
public class AppInterDaoImpl extends DefaultDao implements AppInterDao {
	@Override
	public PageDataForm getAppInterList(DefaultForm in, PageForm page) {
		ConditionDefBuilder cond = new ConditionDefBuilder();
		ParamMap map = new ParamMap();
		if (in != null && in instanceof AppInterForm) {
			AppInterForm appParam = (AppInterForm) in;
			if (!StrKit.isEmpty(appParam.getInterName())) {

				cond.param("T_Interface.name like :name");

				map.put("name", "%" + appParam.getInterName() + "%");
			}
			if (appParam.getValid() >= 0) {
				cond.param("T_Interface.ISVALID=:valid");
				map.put("valid", appParam.getValid());
			}
			if (appParam.getAppID()>0) {
				cond.param("T_Application.ID = :appID");
				map.put("appID", appParam.getAppID());

			}
			if (!StrKit.isEmpty(appParam.getInterfaceType())) {
				cond.param("T_Dict.DValue like :interType");
				map.put("interType", "%" + appParam.getInterfaceType() + "%");
			}

			if (!StrKit.isEmpty(appParam.getDescription())) {
				cond.param(
						"T_Interface.name like :desc or T_Application.name like :desc or T_Dict.DValue like :desc or T_Interface.Description like :desc");
				map.put("desc", "%" + appParam.getDescription() + "%");
			}
		}
		try {
			return queryPage(AppInterForm.class, page, new String[] { "id" }, cond.define(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DefaultForm> getInterList(int appId) {
		return queryList(InterForm.class,
				new ConditionDefBuilder().param("applicationId=:appId").param("T_Interface.isValid=:valid").define(),
				new ParamMap().put("appId", appId).put("valid", 1));
	}

	@Override
	public List<DefaultForm> getInterType() {
		return queryList(SimpleTypeForm.class, new ConditionDefBuilder().param("DType=:dtype and IsValid=1").define(),
				new ParamMap().put("dtype", "02"));
	}

	@Override
	public List<DefaultForm> getInterName(int appId) {
		return queryList(SimpleInterForm.class,
				new ConditionDefBuilder().param("ApplicationId=:appId").param("T_Interface.IsValid=:valid").define(),
				new ParamMap().put("appId", appId).put("valid", 1));
	}

	@Override
	public DefaultForm append(AppInterForm app) {
		if (save(app)) {
			return MsgFrom.info("新增成功!");
		} else{
			return MsgFrom.err("新增失败!");
		}
	}

	@Override
	public DefaultForm edit(AppInterForm app) {
		if (saveUpdate(app)) {
			return MsgFrom.info("修改成功!");
		} else {
			return MsgFrom.err("修改失败!");
		}
	}

	@Override
	public DefaultForm remove(int interfaceID) {
		MsgFrom msg = null;
		if ((msg = hasSubNode(interfaceID)) != null) {
			return msg;
		}
		if (delete(new SimpleInterForm().setId(interfaceID))) {
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
	private MsgFrom hasSubNode(int interfaceID) {
		// 判断是否存在接口
		String sql = "select count(*) from T_InterfaceMethod where interfaceID=?";
		int count = getJdbcTemplate().queryForObject(sql, new Object[] { interfaceID }, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt(1);
			}

		});
		if (count > 0) {
			return MsgFrom.err("此接口存在接口方法,不能删除!<br/>如果此接口已失效请使用编辑功能将其置为[失效]!");
		}

		return null;
	}

	@Override
	public Object download() throws Exception{
		return TemplateDaoImpl.download(this.getClass());
	}

	@Override
	public Object upload(MultipartFile file) throws Exception{
		return this.impExcel(ExcelKit.load(file, 0).title("ApplicationID","Name","InterfaceTypeID","description",
				"CreateEmpID","CreateDate","IsValid")
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
				.addValidator(new RowValidatorList(1).required("不是合法值!").unique())
				.addFormatter(2, new DaoValueFormatter(this) {

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
									if("02".equals(dict.getDtype()))
										idMap.put(dict.getDvalue(), dict.getDkey());
								}

							}
						}
						return idMap;
					}
				})
				.addFormatter(5, new DaoValueFormatter(this) {
					@Override
					public Object format(Object value) {
						// TODO Auto-generated method stub
						return new SimpleDateFormat("yyyy-MM-dd").format(value);
					}})
				.addValidator(new RowValidatorList(6).in(0,1))
				, new SimpleDaoRowMapper(this, AppInterForm.class));
	}

}
