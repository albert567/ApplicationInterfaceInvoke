package com.gwamcc.aii.util.sql2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gwamcc.aii.forms.DefaultForm;
import com.gwamcc.aii.forms.PageForm;
import com.gwamcc.aii.util.ArrayKit;
import com.gwamcc.aii.util.SpElKit;
import com.gwamcc.aii.util.StrKit;
import com.gwamcc.aii.util.sql2.anno.Table;

/**
 * SQL语句拼接类
 * @author 范大德
 *
 */
public class SQLUtils {

	private static Log log = LogFactory.getLog(SQLUtils.class);

	    @SuppressWarnings("rawtypes")
		private static Map cacheMap = new HashMap();
	    @SuppressWarnings("rawtypes")
		private static Map insertSqlCache = new HashMap();
	    @SuppressWarnings("rawtypes")
		private static Map updateSqlCache = new HashMap();
	    @SuppressWarnings("rawtypes")
		private static Map deleteSqlCache = new HashMap();
	    @SuppressWarnings("rawtypes")
		private static Map selectSqlCache = new HashMap();

	    /**
	     * 根据pojo类的class来构建select cols... from 的SQL语句
	     * @param pojoClass
	     * @return
	     */
	    public static String buildSelectCountSql(Class<?> pojoClass){
	    	List<FieldInfo> list=new ArrayList<FieldInfo>();
	    	list.add(new FieldInfo().setDbFieldName("count(*)"));
	        String sql = buildSelectSql(pojoClass,list);
	        if(log.isDebugEnabled()){
	            log.debug("select sql is:"+sql);
	        }
	        return sql;
	    }
	    /**
	     * 根据pojo类的class来构建select count(*) from 的SQL语句
	     * @param pojoClass
	     * @return
	     */
	    public static String buildSelectSql(Class<?> pojoClass){
	        List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
	        String sql = buildSelectSql(pojoClass, fieldInfoList);
	        if(log.isDebugEnabled()){
	            log.debug("select sql is:"+sql);
	        }
	        return sql;
	    }

	    /**
	     * 根据pojo类的class来构建select count(*) from 的SQL语句
	     * @param pojoClass
	     * @return
	     * @throws Exception
	     */
	    public static String buildPageSql(Class<?> pojoClass,PageForm page,String[]orderBy,String where) throws Exception{
	        List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
	        Map<String, String>dbNameMap=new HashMap<String,String>();
	        for(FieldInfo info:fieldInfoList){
	        	dbNameMap.put(info.getPojoFieldName(), info.getDbFieldName());
	        }
	       List<String> sortList=new ArrayList<String>();
        	List<String> orderList=new ArrayList<String>();
	        if(page.getSort()!=null&&page.getSort().length()>0&&page.getOrder()!=null&&page.getOrder().length()>0){
	        	orderBy=null;
	        	String[]sort=page.getSort().split(",");
	        	String[]order=page.getOrder().split(",");
	        	if(sort.length==order.length){
	        		for(String s:sort){
	        			sortList.add(dbNameMap.get(s)==null?s.trim():dbNameMap.get(s).trim());
	        		}
	        		orderList.addAll(ArrayKit.asList(order));
	        	}
	        }

	        if(orderBy!=null&&orderBy.length>0){
        		for(String o:orderBy){
	        		String[]os=o.trim().split(" ");
	        		if(os.length==1){
	        			sortList.add(os[0]);
	        			orderList.add("asc");
	        		}else if(os.length==2){
	        			sortList.add(os[0]);
	        			orderList.add(os[1]);
	        		}else{
	        			System.out.println("order by 子句存在错误,请查看是否存在多余空格:"+o);
	        		}
        		}
    		}

	        int end = page.getPage() * page.getRows();
			int start = (page.getPage() - 1) * page.getRows();
			String tName=loadTableName(pojoClass);
			String inOrder="",outOrder="";

			for(int i=0;i<sortList.size();i++){
				String in=(sortList.get(i).indexOf(" ")>0?sortList.get(i).substring(0, sortList.get(i).indexOf(" ")):sortList.get(i))+" "+orderList.get(i);
				String out=(sortList.get(i).indexOf(" ")>0?sortList.get(i).substring(sortList.get(i).lastIndexOf(" ")+1):sortList.get(i))+" "+orderList.get(i);
				inOrder+=(in.indexOf(".")>0?"":tName+".")+in+",";
				outOrder+= "w2."+(out.indexOf(".")>0?out.substring(out.lastIndexOf(".")+1):out)+",";
			}
			inOrder=inOrder.substring(0,inOrder.lastIndexOf(","));
			outOrder=outOrder.substring(0,outOrder.lastIndexOf(","));

			end = end > 0 ? end : page.getRows();
			start = start >= 0 ? start : 0;
			List<FieldInfo>removeList=new ArrayList<FieldInfo>();
			for(FieldInfo info:fieldInfoList){
				if(info.getDbFieldName().toLowerCase().indexOf("row_number() over")>-1){
					removeList.add(info);
				}
			}
			for(FieldInfo info:removeList){
				fieldInfoList.remove(info);
			}
			FieldInfo rowNum=new FieldInfo();
	        fieldInfoList.add(rowNum.setDbFieldName(" row_number() OVER (order by "+inOrder+") n"));
	        String sql = buildSelectSql(pojoClass, fieldInfoList)+where;

	        String pkName=null;
	        for(FieldInfo info:fieldInfoList){
	        	if(info.isPk()){
	        		pkName=info.getDbFieldName();
	        		break;
	        	}
	        }
	        if(pkName==null){
	        	if(fieldInfoList.contains(rowNum)){
					fieldInfoList.remove(rowNum);
				}
	        	throw new Exception("主键为空不能创建分页数据!");
	        }

	         sql="SELECT w2.* FROM "+tName+" w1,( select TOP "+end+" "
	        		+sql.substring(sql.indexOf("select")+6)+ " ) w2"
	        		+ " WHERE w1."+pkName+" = w2."+pkName+" AND w2.n > "+start+"  order by "+outOrder+" ";
	        if(fieldInfoList.contains(rowNum)){
				fieldInfoList.remove(rowNum);
			}
	        if(log.isDebugEnabled()){
	            log.debug("select sql is:"+sql);
	        }
	        return sql;
	    }

	    /**
	     * 根据pojo类的class来构建insert的SQL语句
	     * @param pojoClass
	     * @return
	     */
	    public static String buildInsertSql(Class<?> pojoClass){
	        List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
	        String sql = buildInsertSql(pojoClass, fieldInfoList);
	        if(log.isDebugEnabled()){
	            log.debug("insert sql is:"+sql);
	        }
	        return sql;
	    }

	    /**
	     * 根据pojo类的class构建根据pk来update的SQL语句
	     * @param pojoObject
	     * @return
	     */
	    public static String buildUpdateSql(Class<?> pojoClass){
	        List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
	        String sql = buildUpdateSqlByPK(pojoClass, fieldInfoList);
	        if(log.isDebugEnabled()){
	            log.debug("update sql is:"+sql);
	        }
	        return sql;
	    }

	    /**
	     * 根据pojo类的Class和更新的条件字段来生成upate的SQL语句
	     * @param pojoClass
	     * @param columns
	     * @return
	     * @throws Exception
	     */
	    public static String buildUpdateSqlByColumns(Class<?> pojoClass,String[] columns) throws Exception{
	        if(null!=columns && columns.length>0){
	            List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
	            String sql = buildUpdateSqlByColumns(pojoClass, fieldInfoList, columns);
	            if(log.isDebugEnabled()){
	                log.debug("update sql is:"+sql);
	            }
	            return sql;
	        }else{
	            if(log.isDebugEnabled()){
	                log.debug("生成update sql error! 参数columns必须有值"  );
	            }
	            throw new Exception("参数columns必须有值！");
	        }
	    }

	    /**
	     * 根据pojo类的Class生成根据pk来delete的SQL语句
	     * @param pojoClass
	     * @return
	     */
	    public static String buildDeleteSql(Class<?> pojoClass){
	        List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
	         String sql = buildDeleteSqlByPK(pojoClass,fieldInfoList);
	         if(log.isDebugEnabled()){
	             log.debug("delete sql is:"+sql);
	         }
	        return sql;
	    }

	    /**
	     * 根据pojo类的Class和更新的条件字段来生成delete的SQL语句
	     * @param pojoClass
	     * @param columns
	     * @return
	     * @throws Exception
	     */
	    public static String buildDeleteSqlByColumns(Class<?> pojoClass,String[] columns) throws Exception{
	        if(null!=columns && columns.length>0){
	            List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
	            String sql = buildDeleteSqlByColumns(pojoClass, fieldInfoList, columns);
	            if(log.isDebugEnabled()){
	                log.debug("delete sql is:"+sql);
	            }
	            return sql;
	        }else{
	            if(log.isDebugEnabled()){
	                log.debug("生成delete sql error! 参数columns必须有值"  );
	            }
	            throw new Exception("参数columns必须有值！");
	        }
	    }

	    public static FieldInfo getPK(Class<?> pojoClass){
	    	List<FieldInfo> fieldInfoList = loadPojoSqlInfo(pojoClass);
            for(FieldInfo info:fieldInfoList){
            	if(info.isPk()){
            		return info;
            	}
            }
            FieldInfo id=new FieldInfo();
            id.setDbFieldName("ID");
            id.setPojoFieldName("id");
            id.setPk(true);
            return id;
	    }

	    public static List<Object> getFieldValues(FieldInfo fieldInfo,List<DefaultForm> list) throws Exception{
	    	if(list==null){
	    		return null;
	    	}
	    	if(list.size()==0){
	    		return new ArrayList<Object>();
	    	}
	    	if(fieldInfo.getPojoFieldName()==null){
        		return null;
        	}
	    	String fieldName = fieldInfo.getPojoFieldName();
            String getMethoName = fieldInfo.getType().equals(Boolean.class)?"is":"get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method m= list.get(0).getClass().getMethod(getMethoName);

	    	List<Object>retList=new ArrayList<Object>();
	    	for(Object o:list){
	    		retList.add(m.invoke(o));
	    	}
           return retList;
	    }

	    public static Object getFieldValue(FieldInfo fieldInfo,Object bean) throws Exception{
	    	if(fieldInfo.getPojoFieldName()==null){
        		return null;
        	}
            String fieldName = fieldInfo.getPojoFieldName();
            String getMethoName = fieldInfo.getType().equals(Boolean.class)?"is":"get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method m= bean.getClass().getMethod(getMethoName);
	        return m.invoke(bean);
	    }

	    /**
	     * 将bean转换为map
	     * @param map
	     * @param pojoClass
	     * @return
	     * @throws Exception
	     */
		public static Map<String, Object> covertBeanToMap(Object bean) throws Exception{

	    	Map<String, Object>map=new HashMap<String,Object>();

	        List<FieldInfo> list = loadPojoSqlInfo(bean.getClass());
	        for(FieldInfo fieldInfo : list){
	        	if(fieldInfo.getPojoFieldName()==null){
	        		continue;
	        	}
	            String fieldName = fieldInfo.getPojoFieldName();
	            String getMethoName = fieldInfo.getType().equals(Boolean.class)?"is":"get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	            Method m= bean.getClass().getMethod(getMethoName);
		        map.put(fieldName,m.invoke(bean));
			}
	        return map;
	    }

	    /**
	     * 将SQL查询出来的map对象转成实体对象
	     * @param map
	     * @param pojoClass
	     * @return
	     * @throws Exception
	     */
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public static Object coverMapToBean(Map map,Class pojoClass) throws Exception{
	        Object result = pojoClass.newInstance();

	        List<FieldInfo> list = loadPojoSqlInfo(pojoClass);
	        for(FieldInfo fieldInfo : list){
	        	if(fieldInfo.getPojoFieldName()==null){
	        		continue;
	        	}
	            String dbName = fieldInfo.getDbFieldName().toUpperCase();
	            if(dbName.indexOf(".")>0){
	            	dbName=dbName.substring(dbName.lastIndexOf(".")+1);
	            }
	            if(dbName.indexOf(" ")>0){
	            	dbName=dbName.substring(dbName.lastIndexOf(" ")+1,dbName.length());
	            }
	            String fieldName = fieldInfo.getPojoFieldName();
	            String setMethoName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	            if(map.get(dbName)!=null){

					Method m= pojoClass.getMethod(setMethoName,fieldInfo.getType());
	                Object obj=map.get(dbName);
	                if(!obj.getClass().isAssignableFrom(fieldInfo.getType())||!obj.getClass().equals(fieldInfo.getType())){
	                	if(fieldInfo.getType().equals(String.class)){
	                		obj=obj.toString();
	                	}else if(fieldInfo.getType().equals(Double.class)){
	                		obj=new Double(obj.toString());
	                	}else if(fieldInfo.getType().equals(Float.class)){
	                		obj=new Float(obj.toString());
	                	}
	                }
	                m.invoke(result, obj);
	            }
			}
	        return result;
	    }


	    /**
	     * 加载读取pojo的注解信息
	     * @param pojoClass
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static List<FieldInfo> loadPojoSqlInfo(Class pojoClass){
	        List<FieldInfo> resultList = null;
	        if(null == cacheMap.get(pojoClass.getName())){
	            resultList = new ArrayList<FieldInfo>();

	            Field[] fields = pojoClass.getDeclaredFields();
	            for(Field field : fields){
	                FieldInfo fieldInfo = new FieldInfo();
	                fieldInfo.setPojoFieldName(field.getName());

	                if(field.isAnnotationPresent(com.gwamcc.aii.util.sql2.anno.Field.class)){
	                    String value =SpElKit.str(((com.gwamcc.aii.util.sql2.anno.Field)field.getAnnotation(com.gwamcc.aii.util.sql2.anno.Field.class)).value());//得到配置的数据库字段名
	                    if(StrKit.isEmpty(value)){//没有设置数据库的字段名，则取pojo的字段名
	                        fieldInfo.setDbFieldName(lowerStrToUnderline(field.getName()));
	                    }else{
	                        fieldInfo.setDbFieldName(value);
	                    }
	                }else{
	                    fieldInfo.setDbFieldName(lowerStrToUnderline(field.getName()));
	                }

	                if(field.isAnnotationPresent(com.gwamcc.aii.util.sql2.anno.PK.class)){
	                    fieldInfo.setPk(true);
	                }
	                if(field.isAnnotationPresent(com.gwamcc.aii.util.sql2.anno.NoInsert.class)){
	                    fieldInfo.setInsert(false);
	                }
	                if(field.isAnnotationPresent(com.gwamcc.aii.util.sql2.anno.NoUpdate.class)){
	                    fieldInfo.setUpdate(false);
	                }
	                if(field.isAnnotationPresent(com.gwamcc.aii.util.sql2.anno.NoQuery.class)){
	                    fieldInfo.setQuery(false);
	                }
	                if(field.isAnnotationPresent(com.gwamcc.aii.util.sql2.anno.Left.class)){
	                    fieldInfo.setLeft(new LeftInfo()
	                    		.setTableName(
	                    			SpElKit.str(
	                    					((com.gwamcc.aii.util.sql2.anno.Left)field.getAnnotation(com.gwamcc.aii.util.sql2.anno.Left.class)).table()
	                    					)
	                    			)
	                    		.setOnField(
	                    				SpElKit.str(
	                    						((com.gwamcc.aii.util.sql2.anno.Left)field.getAnnotation(com.gwamcc.aii.util.sql2.anno.Left.class)).on())
	                    			)
	                    		.setField(
	                    				SpElKit.strArray(
	                    						((com.gwamcc.aii.util.sql2.anno.Left)field.getAnnotation(com.gwamcc.aii.util.sql2.anno.Left.class)).fields())
	                    			)
	                    		.setSeq(
	                    				SpElKit.integer(
	                    							((com.gwamcc.aii.util.sql2.anno.Left)field.getAnnotation(com.gwamcc.aii.util.sql2.anno.Left.class)).seq())
	                    			));
	                }

	                fieldInfo.setType(field.getType());

	                resultList.add(fieldInfo);
	            }
	            if(tableHasUUID(pojoClass)){
	            	 //添加uuid
		            FieldInfo fieldInfo = new FieldInfo();
		            fieldInfo.setDbFieldName(loadTableName(pojoClass)+".UUID");
		            fieldInfo.setPojoFieldName("uuid");
		            fieldInfo.setUpdate(false);
		            fieldInfo.setType(String.class);
		            resultList.add(fieldInfo);
	            }
	            cacheMap.put(pojoClass.getName(), resultList);
	        }else{
	            resultList = (List<FieldInfo>)cacheMap.get(pojoClass.getName());
	        }

	        return resultList;
	    }

	    public static String getFieldName(String tableName,String field){
	    	if(field.indexOf(".")<0&&field.indexOf("(")<0){
        		String t=tableName;
        		if(tableName.toUpperCase().indexOf(" ")>0){
        			t=tableName.substring(tableName.lastIndexOf(" "));
        		}
        		field=t+"."+field;
        	}
	    	if(field.split("\\.").length>2&&field.indexOf(" ")<0&&field.indexOf("(")<0){
	    		String[] fields = field.split("\\.");
	    		return fields[fields.length-2]+"."+fields[fields.length-1];
	    	}
	    	return field;
	    }

	    /**
	     * 拼接select语句
	     * @param pojoClass
	     * @param fieldInfoList
	     * @return
	     */
	    @SuppressWarnings("rawtypes")
		private static String buildSelectSql(Class pojoClass,List<FieldInfo> fieldInfoList){
	        if(selectSqlCache.get(pojoClass.getName()) != null){
	            return (String)selectSqlCache.get(pojoClass.getName());
	        }
	        String tableName=loadTableName(pojoClass);
	        String f="";
	        Map<Integer,String> left=new HashMap<Integer,String>();
	        String leftStr="";
	        for(FieldInfo field:fieldInfoList){
	        	String dbName=getFieldName(tableName ,field.getDbFieldName());
	        	if(field.isQuery()){
			        f+=dbName+",";
	        	}
		        if(field.getLeft()!=null){
	        		String lName=field.getLeft().getOnField();
 	        		lName=getFieldName(field.getLeft().getTableName(),lName);
	        		left.put(field.getLeft().getSeq(),(left.get(field.getLeft().getSeq())==null?"":left.get(field.getLeft().getSeq()))+ " left join "+field.getLeft().getTableName()+" on "+dbName+"="+lName+" ");
	        	}
		        if("count(*)".equalsIgnoreCase(dbName)){
		        	for(FieldInfo f2:loadPojoSqlInfo(pojoClass)){
		        		 if(f2.getLeft()!=null){
		 	        		String lName=f2.getLeft().getOnField();
		 	        		lName=getFieldName(f2.getLeft().getTableName(),lName);
		 	        		left.put(f2.getLeft().getSeq(),
		 	        				(left.get(f2.getLeft().getSeq())==null?"":left.get(f2.getLeft().getSeq()))
		 	        				+" left join "
		 	        				+f2.getLeft().getTableName()+" on "+f2.getDbFieldName()+"="+lName+" ");
		 	        	}
		        	}
		        }
	        }
	        Integer[]seqs=left.keySet().toArray(new Integer[0]);
	        for(int i=0;i<seqs.length;i++){
	        	for(int j=0;j<seqs.length;j++){
	        		if(seqs[i]<seqs[j]){
	        			int x=seqs[i];
	        			seqs[i]=seqs[j];
	        			seqs[j]=x;
	        		}
	        	}
	        }
	        for(int seq:seqs){
	        	leftStr+=left.get(seq);
	        }
	        left=new HashMap<Integer,String>();
	        return "select "+f.substring(0, f.length()-1)+" from " +tableName +" "+leftStr;
	    }

	    /**
	     * 拼接insert的SQL
	     * @param pojoClass
	     * @param fieldInfoList
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    private static String buildInsertSql(Class pojoClass,List<FieldInfo> fieldInfoList){
	        String result = null;
	        if(insertSqlCache.get(pojoClass.getName()) != null){
	            result = (String)insertSqlCache.get(pojoClass.getName());
	            return result;
	        }

	        String tableName = loadTableName(pojoClass);

	        StringBuffer temp1 = new StringBuffer();
	        StringBuffer temp2 = new StringBuffer();
	        for(FieldInfo fieldInfo : fieldInfoList){
	            if(fieldInfo.isInsert()){
	                temp1.append(fieldInfo.getDbFieldName()).append(",");
	                temp2.append(":").append(fieldInfo.getPojoFieldName()).append(",");
	            }
	        }
	        temp1.deleteCharAt(temp1.length()-1);
	        temp2.deleteCharAt(temp2.length()-1);

	        StringBuffer resultSql = new StringBuffer();
	        resultSql.append("insert into ");
	        resultSql.append(tableName);
	        resultSql.append("(");
	        resultSql.append(temp1);
	        resultSql.append(") values (");
	        resultSql.append(temp2);
	        resultSql.append(")");

	        result = resultSql.toString();
	        insertSqlCache.put(pojoClass.getName(), result);
	        return result;
	    }

	    /**
	     * 生成根据主键生成删除的SQL
	     * @param pojoClass
	     * @param fieldInfoList
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    private static String buildDeleteSqlByPK(Class pojoClass,List<FieldInfo> fieldInfoList){
		if (log.isInfoEnabled()) {
			log.info("buildDeleteSqlByPK(Class, List<FieldInfo>) - List<FieldInfo> fieldInfoList=" + fieldInfoList); //$NON-NLS-1$
		}

	        String result = null;
	        if(deleteSqlCache.get(pojoClass.getName()+"_pk") != null){
	            result = (String)deleteSqlCache.get(pojoClass.getName());
	            if (log.isInfoEnabled()) {
	    			log.info("buildDeleteSqlByPK(Class, List<FieldInfo>) - List<FieldInfo> result=" + result); //$NON-NLS-1$
	    		}
	            if(result!=null){
	            	return result;
	            }
	        }

	        StringBuffer resultSql = new StringBuffer();
	        resultSql.append(appendBaseDeleteSQL(pojoClass));

	        for(FieldInfo fieldInfo : fieldInfoList){
	            if(fieldInfo.isPk()){
	                resultSql.append(fieldInfo.getDbFieldName());
	                resultSql.append("=:").append(fieldInfo.getPojoFieldName()).append(" and ");
	            }
	        }
	        resultSql.delete(resultSql.length()-4, resultSql.length());
	        result = resultSql.toString();
	        deleteSqlCache.put(pojoClass.getName()+"_pk", result);

	        return result;
	    }

	    /**
	     * 拼接根据主键来update的SQL
	     * @param pojoClass
	     * @param fieldInfoList
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    private static String buildUpdateSqlByPK(Class pojoClass, List<FieldInfo> fieldInfoList){
	        String result = null;
	        if(updateSqlCache.get(pojoClass.getName()+"_pk") != null){
	            result = (String)updateSqlCache.get(pojoClass.getName()+"_pk");
	            return result;
	        }

	        StringBuffer resultSql = new StringBuffer();
	        resultSql.append(appendBaseUpdateSQL(pojoClass, fieldInfoList));

	        for(FieldInfo fieldInfo : fieldInfoList){
	            if(fieldInfo.isPk()){
	                resultSql.append(fieldInfo.getDbFieldName());
	                resultSql.append("=:").append(fieldInfo.getPojoFieldName()).append(" and ");
	            }
	        }
	        resultSql.delete(resultSql.length()-4, resultSql.length());
	        result = resultSql.toString();
	        updateSqlCache.put(pojoClass.getName()+"_pk", result);

	        return result;
	    }

	    /**
	     * 根据用户指定的更新条件(字段)来生成update的SQL
	     * @param pojoClass
	     * @param fieldInfoList
	     * @param columns
	     * @return
	     */
	    @SuppressWarnings("rawtypes")
		private static String buildUpdateSqlByColumns(Class pojoClass, List<FieldInfo> fieldInfoList,String[] columns){
	        StringBuffer resultSql = new StringBuffer();
	        if(updateSqlCache.get(pojoClass.getName()+"_columns") != null){
	            resultSql.append((String)updateSqlCache.get(pojoClass.getName()+"_columns"));
	        }else{
	            resultSql.append(appendBaseUpdateSQL(pojoClass, fieldInfoList));
	        }

	        for(String column : columns){
	            for(FieldInfo fieldInfo : fieldInfoList){
	                if(column.equals(fieldInfo.getPojoFieldName())){
	                    resultSql.append(fieldInfo.getDbFieldName());
	                    resultSql.append("=:").append(column).append(" and ");
	                    break;
	                }
	            }
	        }
	        resultSql.delete(resultSql.length()-4, resultSql.length());
	        return resultSql.toString();
	    }

	    /**
	     * 拼接update语句的where之前的sql
	     * @param pojoClass
	     * @param fieldInfoList
	     * @param resultSql
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    private static String appendBaseUpdateSQL(Class pojoClass, List<FieldInfo> fieldInfoList){
	        String result = null;
	        if(updateSqlCache.get(pojoClass.getName()+"_columns") != null){
	            result = (String)updateSqlCache.get(pojoClass.getName()+"_columns");
	        }else{
	            StringBuffer resultSql = new StringBuffer();
	            String tableName = loadTableName(pojoClass);

	            resultSql.append("update ").append(tableName).append(" set ");
	            for(FieldInfo fieldInfo : fieldInfoList){
	                if(fieldInfo.isUpdate() && !fieldInfo.isPk()){
	                    resultSql.append(fieldInfo.getDbFieldName());
	                    resultSql.append("=:").append(fieldInfo.getPojoFieldName()).append(",");
	                }
	            }
	            resultSql.deleteCharAt(resultSql.length()-1);
	            resultSql.append(" where ");

	            result = resultSql.toString();
	            updateSqlCache.put(pojoClass.getName()+"_columns", result);
	        }
	        return result;
	    }

	    /**
	     * 根据用户指定的更新条件(字段)来生成delete的SQL
	     * @param pojoClass
	     * @param fieldInfoList
	     * @param columns
	     * @return
	     */
	    @SuppressWarnings("rawtypes")
		private static String buildDeleteSqlByColumns(Class pojoClass, List<FieldInfo> fieldInfoList,String[] columns){
	        StringBuffer resultSql = new StringBuffer();
	        if(deleteSqlCache.get(pojoClass.getName()+"_columns") != null){
	            resultSql.append((String)deleteSqlCache.get(pojoClass.getName()+"_columns"));
	        }else{
	            resultSql.append(appendBaseUpdateSQL(pojoClass, fieldInfoList));
	        }

	        for(String column : columns){
	            for(FieldInfo fieldInfo : fieldInfoList){
	                if(column.equals(fieldInfo.getPojoFieldName())){
	                    resultSql.append(fieldInfo.getDbFieldName());
	                    resultSql.append("=:").append(column).append(" and ");
	                    break;
	                }
	            }
	        }
	        resultSql.delete(resultSql.length()-4, resultSql.length());
	        return resultSql.toString();
	    }

	    /**
	     * 拼接delete语句的where之前的sql
	     * @param pojoClass
	     * @param fieldInfoList
	     * @param resultSql
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    private static String appendBaseDeleteSQL(Class pojoClass){
	        if(deleteSqlCache.get(pojoClass.getName()+"_columns") != null){
	            return (String)deleteSqlCache.get(pojoClass.getName()+"_columns");
	        }else{
	            String result = "delete from " + loadTableName(pojoClass) + " where ";
	            deleteSqlCache.put(pojoClass.getName()+"_columns", result);
	            return result;
	        }
	    }

	    /**
	     * 通过类获取表名
	     * @param pojoClass
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static boolean tableHasUUID(Class pojoClass){
	        if(pojoClass.isAnnotationPresent(Table.class)){
	            Table table = (Table)pojoClass.getAnnotation(Table.class);
	            return table.hasUuid();
	        }else{
	            return true;
	        }
	    }

	    /**
	     * 通过类获取表名
	     * @param pojoClass
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static String loadTableName(Class pojoClass){
	        if(pojoClass.isAnnotationPresent(Table.class)){
	            Table table = (Table)pojoClass.getAnnotation(Table.class);
	            return table.value();
	        }else{
	            return lowerStrToUnderline(pojoClass.getSimpleName());
	        }
	    }

	    /**
	     * 将大写字母转换成下划线加小写字母
	     * 例:userName--> user_name
	     * @param str
	     * @return
	     */
	    private static String lowerStrToUnderline(String str) {
	    	/*
	        if(StrKit.isEmpty(str)){
	            return "";
	        }
	        StringBuilder sb = new StringBuilder(str);
	        char c;
	        int count = 0;
	        for (int i = 0; i < str.length(); i++) {
	            c = str.charAt(i);
	            if (c >= 'A' && c <= 'Z') {
	                sb.replace(i+count, i+count+1, (c+"").toLowerCase());
	                sb.insert(i+count, "_");
	                count++;
	            }
	        }
	        return sb.toString();*/
	    	return str;
	    }
}

