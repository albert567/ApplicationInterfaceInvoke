package com.gwamcc.aii.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.TemplateDao;
import com.gwamcc.aii.forms.SimpleTypeForm;
import com.gwamcc.aii.util.ClassKit;
import com.gwamcc.aii.util.HttpKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.http.ZipFileItem;
import com.gwamcc.aii.util.http.ZipFileList;
import com.gwamcc.aii.util.template.anno.Template;
/**
 * 模板下载操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class TemplateDaoImpl implements TemplateDao {

	private static Map<String, Template>templateMap=null;
	@Override
	public Object download(String[] names) throws Exception {
		if(templateMap==null){
			scan();
		}
		if(names==null){
			return null;
		}

		return HttpKit.zip(new ZipFileList("模板.zip").addArray(names,new ZipFileList.OnArrayItemAdd<String>() {

			@Override
			public ZipFileItem convert(String[] array, int index, String item) {
				Template temp=templateMap.get(item);
				if(temp!=null){
					return new ZipFileItem("/templates/",temp.key(),temp.value());
				}
				return null;
			}

		}));
	}



	public static Object download(Class<?>type) throws Exception {
		if(templateMap==null){
			scan();
		}
		String name=StrKit.md5(type.getName(),"password");
		if(templateMap.get(name)==null){
			return null;
		}
		Template temp=templateMap.get(name);
		return HttpKit.download("templates\\"+temp.key(),temp.value());
	}



	private static Map<String,Template> scan() throws ClassNotFoundException{
		if(templateMap==null){
			templateMap=new HashMap<String, Template>();
			Set<String>classNameSet=ClassKit.scanBasePackage("com.gwamcc.aii.dao.impl");
			for(String className:classNameSet){
				Class<?> c=Class.forName(className);
				if(c.isAnnotationPresent(Template.class)){
					Template type = (Template)c.getAnnotation(Template.class);

					templateMap.put(StrKit.md5(c.getName(),"password"),type);
				}
			}
		}
		return templateMap;
	}

	@Override
	public Object list() throws Exception {
		if(templateMap==null){
			scan();
		}
		List<SimpleTypeForm>list=new ArrayList<SimpleTypeForm>();
		for(String key:templateMap.keySet()){
			list.add(new SimpleTypeForm().setId(key).setName(templateMap.get(key).value()));
		}
		return list;
	}



	@Override
	public Object download(String name) throws Exception {
		if(templateMap==null){
			scan();
		}
		if(templateMap.get(name)==null){
			return null;
		}
		Template temp=templateMap.get(name);
		return HttpKit.download("templates\\"+temp.key(),temp.value());
	}


}
