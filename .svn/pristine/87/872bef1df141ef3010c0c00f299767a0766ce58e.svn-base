package com.gwamcc.aii.dao.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwamcc.aii.dao.LogDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.LogQueryForm;
import com.gwamcc.aii.forms.LogQueryParamForm;
import com.gwamcc.aii.forms.MsgFrom;
import com.gwamcc.aii.forms.PageDataForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.forms.PageResultForm;
import com.gwamcc.aii.forms.SimpleTypeForm;
import com.gwamcc.aii.util.ArrayKit;
import com.gwamcc.aii.util.ClassKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.form.anno.QueryForm;
import com.gwamcc.aii.util.form.anno.Title;
import com.gwamcc.aii.util.form.bean.FormTitle;
import com.gwamcc.aii.util.sql2.ConditionDef;
import com.gwamcc.aii.util.sql2.LikeParam;
import com.gwamcc.aii.util.sql2.LikeSpliceType;
import com.gwamcc.aii.util.sql2.LikeType;
import com.gwamcc.aii.util.sql2.ParamMap;
import com.gwamcc.aii.util.sql2.QueryLike;
import com.gwamcc.aii.util.sql2.RowMap;
import com.gwamcc.aii.util.sql2.RowMapper;
import com.gwamcc.aii.util.sql2.SQLUtils;

import config.Res;

/**
 * 日志数据操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class LogDaoImpl extends DefaultDao implements LogDao {

	private static Map<String, Class<?>> formMap = null;

	public LogDaoImpl() {
	}

	@Override
	public Object tabs(String uuid) throws Exception {
		if (formMap == null) {
			scan();
		}
		List<Object> list = queryOneFieldList("select dataTable from T_Log where uuid=:uuid group by dataTable",
				new ParamMap().put("uuid", uuid));
		List<SimpleTypeForm> tabs = new ArrayList<SimpleTypeForm>();
		for (Object o : list) {
			if (o != null) {
				String key = StrKit.md5(o.toString().toLowerCase(), "log");
				if (formMap.containsKey(key)) {
					QueryForm form = form(formMap.get(key));
					tabs.add(new SimpleTypeForm().setId(key).setName(form.title()));
				}

			}
		}
		return tabs;
	}

	@Override
	public Object tabs() throws Exception {
		if (formMap == null) {
			scan();
		}
		List<Object> list = queryOneFieldList("select dataTable from T_Log group by dataTable", new ParamMap());
		List<SimpleTypeForm> tabs = new ArrayList<SimpleTypeForm>();
		for (Object o : list) {
			if (o != null) {
				String key = StrKit.md5(o.toString().toLowerCase(), "log");
				if (formMap.containsKey(key)) {
					QueryForm form = form(formMap.get(key));
					tabs.add(new SimpleTypeForm().setId(key).setName(form.title()));
				}

			}
		}
		return tabs;
	}

	@Override
	public Object title(String type) throws Exception {
		if (formMap == null) {
			scan();
		}
		Class<?> form = formMap.get(type);
		return getTitleInfo(form);
	}

	@Override
	public Object listByUuid(String table, String uuid, PageForm page) throws Exception {
		if (formMap == null) {
			scan();
		}
		Class<?> fromClass = formMap.get(table);
		String tableName = SQLUtils.loadTableName(fromClass);
		return queryPage(fromClass, page, new String[] { "ID" },
				new ConditionDef(new Object[][] {
						{ tableName + ".uuid in (select datauuid from T_log where uuid=:uuid and datatable='"
								+ tableName + "')" } }),
				new ParamMap().put("uuid", uuid));
	}

	@Override
	public Object list(LogQueryParamForm query, PageForm page) throws Exception {
		setPrintSql(true);
		if (formMap == null) {
			scan();
		}

		Class<?> fromClass = formMap.get(query.getType());
		String tableName = SQLUtils.loadTableName(fromClass);

		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");

		PageDataForm pdata = queryPage(LogQueryForm.class, page, new String[] { "logtime desc", "id desc" },
				new ConditionDef(new Object[][] {
					{ "dataTable=:table" },
					{"(tblUser.UserName in (:user) or '' in(:user))"},
					{"(dataOperation in (:dataOperation))"},
					{"logTime >= :start"},
					{"logTime < :end"},
					{"dataUUID in("+QueryLike.bulidQuerySql(fromClass, "desc")+") OR logMsg like :desc"}
				}), ParamMap.map("table", tableName).put("user",query.getUser().split(" "))
				.put("dataOperation", query.getOperation().split(","))
				.put("start", df.parse(query.getStartTime()))
				.put("end", df.parse(query.getEndTime()))
				.put("desc",LikeParam.toArray(query.getKeyword().split(" "), LikeType.AND, LikeSpliceType.START_END)));

		PageResultForm ret = PageResultForm.create(pdata,
				new RowMapper(null) {
					@SuppressWarnings("unchecked")
					@Override
					public RowMap mapper(RowMap map) throws Exception {
						String op = map.getString("dataOperation");
						if (ArrayKit.in(op, Res.LOG.OPERATION_INSERT,Res.LOG.OPERATION_IMPORT)) {
							map.putAll((Map<String,Object>)new ObjectMapper().readValue(map.getString("logMsg"), new TypeReference<Map<String, Object>>(){}));
						} else if (ArrayKit.in(op, Res.LOG.OPERATION_DELETE,Res.LOG.OPERATION_ROLLBACK)) {

							Map<String,Object>beanMap= (Map<String,Object>)new ObjectMapper().readValue(map.getString("logMsg"), new TypeReference<Map<String, Object>>(){});
							for(String key:beanMap.keySet()){
								beanMap.put(key, "<span style='font-size: 12px;color: #aaa''>"+beanMap.get(key)+"</span>");
							}
							map.putAll(beanMap);
						} else if (Res.LOG.OPERATION_UPDATE.equals(op)) {
							JsonNode node = new ObjectMapper().readTree(map.getString("logMsg"));
							// System.out.println(node.get("info").toString());
							map.putAll((Map<String,Object>)new ObjectMapper().readValue(node.get("info").toString(), new TypeReference<Map<String, Object>>(){}));

							JsonNode change = node.get("change");
							Iterator<String> it = change.fieldNames();
							while (it.hasNext()) {
								String name = it.next();
								map.put(name, "<span style='font-size: 12px;color: #aaa'><s>"+map.get(name) + "</s></span><span style='font-size: 12px;color:red'>" + change.get(name).asText()+"</span>");
							}
						}
						return map;
					}
				});

		return ret;
	}

	@Override
	public Object rollback(String uuid) throws Exception {
		rollbackFromLog(uuid);
		return MsgFrom.info("回滚成功!");
	}

	private static List<FormTitle> getTitleInfo(Class<?> pojoClass) {
		List<FormTitle> resultList = new ArrayList<FormTitle>();
		Field[] fields = pojoClass.getDeclaredFields();
		for (Field field : fields) {
			FormTitle title = new FormTitle();
			title.setField(field.getName());
			if (field.isAnnotationPresent(Title.class)) {
				Title t = field.getAnnotation(Title.class);
				title.setTitle(t.value()).setHidden(t.hidden()).setFormatter(t.formatter()).setCheckbox(t.checkbox());
			} else {
				title.setTitle(field.getName()).setHidden(true);
			}
			resultList.add(title);
		}
		return resultList;
	}

	private static QueryForm form(Class<?> c) {
		if (c.isAnnotationPresent(QueryForm.class)) {
			return (QueryForm) c.getAnnotation(QueryForm.class);
		}
		return null;
	}

	private static Map<String, Class<?>> scan() throws ClassNotFoundException {
		if (formMap == null) {
			formMap = new HashMap<String, Class<?>>();
			Set<String> classNameSet = ClassKit.scanBasePackage("com.gwamcc.aii.forms");
			for (String className : classNameSet) {
				Class<?> c = Class.forName(className);
				if (c.isAnnotationPresent(QueryForm.class)) {
					QueryForm form = (QueryForm) c.getAnnotation(QueryForm.class);
					String formName = form.value();
					if (formName.length() == 0) {
						formName = SQLUtils.loadTableName(c);
					}
					formMap.put(StrKit.md5(formName.toLowerCase(), "log"), c);
				}
			}
		}
		return formMap;
	}

	@Override
	public Object history(String datauuid) throws Exception {
		return null;
	}

	@Override
	public Object operations() throws Exception {
		return StrKit.toString(ArrayKit.toKeyValueList(Res.LOG.OPERATIONS, false));
	}
}
