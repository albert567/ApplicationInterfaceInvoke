package com.gwamcc.aii.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpElKit {
	public static Object getValue(Object exp ){
		ExpressionParser parser=new SpelExpressionParser();
		Expression e=parser.parseExpression(exp.toString(),ParserContext.TEMPLATE_EXPRESSION);
		return e.getValue();
	}
	public static String str(Object exp){

		Object val=getValue(exp);
		return val==null?null:val.toString();
	}
	public static int integer(Object exp){
		return Integer.parseInt(str(exp));
	}

	public static String[]strArray(Object[]exp ){
		List<String>list=new ArrayList<String>();
		for(Object e:exp){
			list.add(str(e));
		}
		return list.toArray(new String[0]);
	}
}
