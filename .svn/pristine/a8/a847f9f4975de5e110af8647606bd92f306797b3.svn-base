package com.gwamcc.aii.util.sql2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QueryLike {

	public static String bulidQuerySql(Class<?>cs,String likeName) throws Exception{
		String tableName=SQLUtils.loadTableName(cs);
		List<FieldInfo>fieldInfoList=SQLUtils.loadPojoSqlInfo(cs);
        List<String> fList=new ArrayList<String>();
        Map<Integer,String> left=new HashMap<Integer,String>();
        String leftStr="";
        String where="(";
        for(FieldInfo field:fieldInfoList){
        	String dbName=SQLUtils.getFieldName(tableName ,field.getDbFieldName());
        	if(field.isQuery()&&dbName.indexOf("(")<0){
        		fList.add(dbName.indexOf(" ")>0&&dbName.indexOf("(")<0?dbName.substring(0,dbName.indexOf(" ",1)):dbName);
        	}
	        if(field.getLeft()!=null){
        		String lName=field.getLeft().getOnField();
	        		lName=SQLUtils.getFieldName(field.getLeft().getTableName(),lName);
        		left.put(field.getLeft().getSeq(),(left.get(field.getLeft().getSeq())==null?"":left.get(field.getLeft().getSeq()))+ " left join "+field.getLeft().getTableName()+" on "+dbName+"="+lName+" ");
        	}
	        if("count(*)".equalsIgnoreCase(dbName)){
	        	for(FieldInfo f2:SQLUtils.loadPojoSqlInfo(cs)){
	        		 if(f2.getLeft()!=null){
	 	        		String lName=f2.getLeft().getOnField();
	 	        		lName=SQLUtils.getFieldName(f2.getLeft().getTableName(),lName);
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
        for(String f:fList){
        	where +=" ("+f+" like :"+likeName+" ) OR";
        }
        return "select "+tableName+".UUID from " +tableName +" "+leftStr+" where "+where.substring(0,where.length()-2)+")";
	}
}
