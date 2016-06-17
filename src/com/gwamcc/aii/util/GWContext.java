package com.gwamcc.aii.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.gwamcc.aii.forms.LoginUser;

public class GWContext {
	public static LoginUser getUser(){
		return (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
