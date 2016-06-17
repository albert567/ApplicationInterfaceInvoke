package com.gwamcc.aii.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.gwamcc.aii.dao.JTopoDao;
import com.gwamcc.aii.dao.core.DefaultDao;
import com.gwamcc.aii.forms.TopoNode;
import com.gwamcc.aii.util.ClassKit;
import com.gwamcc.aii.util.jtopo.JTopoSubNodeGetter;
import com.gwamcc.aii.util.jtopo.anno.JTopoSubType;

/**
 * JTopo数据操作实现类
 *
 * @author 范大德
 *
 */
@Repository
public class JTopoDaoImpl extends DefaultDao implements JTopoDao {
	/**
	* Logger for this class
	*/
	private static final Log logger = LogFactory.getLog(JTopoDaoImpl.class);

	private static Map<String, Class<? extends JTopoSubNodeGetter>>subClassMap=null;


	@Override
	public TopoNode getSub(String type, int id,TopoNode pNode) throws Exception {
		if(subClassMap==null){
			scan();
		}
		Class<? extends JTopoSubNodeGetter>c=subClassMap.get(type.toUpperCase());
		if(c==null){
			if (logger.isInfoEnabled()) {
				logger.info("getSub(String, int)---没有找到对应的节点类型:"+type);
			}

			return pNode.setSub(new ArrayList<TopoNode>());
		}
		JTopoSubNodeGetter getter= c.newInstance();
		return getter.getSub(this, id,pNode);
	}


	@SuppressWarnings("unchecked")
	private Map<String, Class<? extends JTopoSubNodeGetter>> scan() throws ClassNotFoundException{

		if(subClassMap==null){
			subClassMap=new HashMap<String, Class<? extends JTopoSubNodeGetter>>();
			Set<String>classNameSet=ClassKit.scanBasePackage("com.gwamcc.aii.util.jtopo.impl");
			for(String className:classNameSet){
				Class<?> c=Class.forName(className);
				if(c.isAnnotationPresent(JTopoSubType.class)){
					JTopoSubType type = (JTopoSubType)c.getAnnotation(JTopoSubType.class);
					type.value();
					Class<? extends JTopoSubNodeGetter>getter=(Class<? extends JTopoSubNodeGetter>) c;
					subClassMap.put(type.value().toString(), getter);
				}
			}
		}
		return subClassMap;
	}


}
