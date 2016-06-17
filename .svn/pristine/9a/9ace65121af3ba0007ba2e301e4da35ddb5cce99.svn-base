package com.gwamcc.aii.util.sql2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;



/**
 * 条件语句拼接工具类
 * @author 范大德
 *
 */
public class Condition {
    public static final String AND = " AND ";
    public static final String OR = " OR ";
    public static final char PREFIX_MATCH = 'P';
    public static final char SUFFIX_MATCH = 'S';
    public static final char GLOBAL_MATCH = 'G';
    public static final char LIKE_MODE = 'L';
    public static final char STANDARD_MODE = 0;

    List<Integer> varTypesList = new ArrayList<Integer>();
    private String conditionClauseStr = "";
    private String relateOperate = AND;
    public Condition(ConditionDef def, Map<String, Object> valueMap) {
        this(def, valueMap, AND);
    }
    public Condition(ConditionDef def, Map<String, Object> valueMap, String relateOperate) {
        this.relateOperate = relateOperate;
        String[] varNameArr = def.getConditionVarNames();
        List<String> usedSubConditionClauseList = new ArrayList<String>();

        for (String varName : varNameArr) {
        	if(valueMap.get(varName)==null){
        		System.out.println("指定了参数["+varName+"],但并未赋值!");
        	}
            if (!StringUtils.isEmpty(valueMap.get(varName).toString())) {

            	Object priValue =  valueMap.get(varName);
            	if(valueMap.get(varName).getClass().isArray()){
            		String sql=def.getSubConditionClause(varName);
            		if(sql.toLowerCase().indexOf(" in")>=0){
	            		Object[]vals=(Object[])valueMap.get(varName);
	            		String names="";
	            		for(int i=0;i<vals.length;i++){
	            			names+=",:"+varName+i;
	            		}
	            		names=names.substring(1);
	            		usedSubConditionClauseList.add(def.getSubConditionClause(varName).replaceAll(":"+varName, names));
            		}else if(sql.toLowerCase().indexOf(" like")>=0){
            			Object[]vals=(Object[])valueMap.get(varName);
	            		String names="";
	            		for(int i=0;i<vals.length;i++){
	            			Object val=vals[i];
	            			LikeParam likeVal;
	            			if (!(val instanceof LikeParam)) {
								likeVal=new LikeParam(val);
							}else{
								likeVal=(LikeParam)val;
							}
	            			String type=" ";
	            			if(i!=0){
	            					type=" "+likeVal.getLikeType().toString()+" ";
	            			}
	            			names+=type+sql+i;
	            		}
	            		names="("+names+")";
	            		usedSubConditionClauseList.add(names);
            		}
            	}else{
            		usedSubConditionClauseList.add(def.getSubConditionClause(varName));
            	}
                if (def.isExistClassTypeInfo(varName)) {
                    Class<?> targetClass = def.getClassTypeInfo(varName);
                    Object newValue = null;
                    if (targetClass == java.sql.Date.class) {
                        newValue = java.sql.Date.valueOf((String)priValue);
                        valueMap.put(varName, newValue);
                    } else if (targetClass == java.sql.Timestamp.class) {
                        newValue = java.sql.Timestamp.valueOf((String)priValue);
                        valueMap.put(varName, newValue);
                    } else if (targetClass == java.sql.Time.class) {
                        newValue = java.sql.Time.valueOf((String)priValue);
                        valueMap.put(varName, newValue);
                    } else if (targetClass == java.util.List.class) {
                        List<Object> valueList=new ArrayList<Object>();
                        if (priValue.getClass().isArray()){
                            Object[] valueArr=(Object[])priValue;
                            for (Object string : valueArr) {
                                valueList.add(string==null?null:string.toString());
                            }
                        }else{
                            valueList.add(priValue);
                        }
                        valueMap.put(varName, valueList);
                    }
                }
                if (def.isExistMatchModeInfo(varName)) {
                    List<Character> matchModeList = def.getMatchModeInfo(varName);
                    if (matchModeList.size() == 1) {
                        if (matchModeList.get(0) == Condition.GLOBAL_MATCH) {
                            priValue = "%" + priValue + "%";
                        } else if (matchModeList.get(0) == Condition.PREFIX_MATCH) {
                            priValue = priValue + "%";
                        } else if (matchModeList.get(0) == Condition.SUFFIX_MATCH) {
                            priValue = "%" + priValue;
                        }
                        valueMap.put(varName , priValue);
                    } else {
                        for (char currMatchMode : matchModeList) {
                            if (currMatchMode == Condition.GLOBAL_MATCH) {
                                String newValue = "%" + priValue + "%";
                                valueMap.put(varName + "_globalMatch", newValue);
                            } else if (currMatchMode == Condition.PREFIX_MATCH) {
                                String newValue = priValue + "%";
                                valueMap.put(varName + "_prefixMatch", newValue);
                            } else if (currMatchMode == Condition.SUFFIX_MATCH) {
                                String newValue = "%" + priValue;
                                valueMap.put(varName + "_suffixMatch", newValue);
                            }
                        }
                    }
                }
            }
        }
        this.conditionClauseStr = StringUtils.join(usedSubConditionClauseList.toArray(), relateOperate);

    }
    public String getConditionClause() {
        return this.conditionClauseStr;
    }
    public String getConditionClauseWithWhere() {
        return "".equals(conditionClauseStr)?"":" WHERE " + conditionClauseStr;
    }
    public String getConditionClauseWithStartRelateOperatorIfNeeded() {
        if(conditionClauseStr.trim().equals("")) {
            return "";
        }else {
            return this.relateOperate + " " + conditionClauseStr;
        }

    }
    public String getConditionClauseWithRelateOperatorAtStart() {
        return this.relateOperate + " " + conditionClauseStr;
    }
    public Integer[] getConditionVarTypes() {
        return varTypesList.toArray(new Integer[]{});
    }
}