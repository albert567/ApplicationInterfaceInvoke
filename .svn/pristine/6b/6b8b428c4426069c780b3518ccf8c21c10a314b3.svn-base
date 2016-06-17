package com.gwamcc.aii.util;



import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 字符串处理类
 * @author 范大德
 *
 */
public class StrKit {
	/**
	 * 判断字符串是否为空字符
	 * @param str	待判断字符串
	 * @return
	 */
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	/**
	 * 将Object转置为Json字符串
	 * @param object	待转置对象
	 * @return
	 */
	public static String toString(Object object){
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return object.toString();
		}
	}
	public static String md5(String val,String s){
		return new Md5PasswordEncoder().encodePassword(val, s);
	}
	public static boolean md5(String md5,String val,String s){
		return new Md5PasswordEncoder().isPasswordValid(md5, val, s);
	}
}
