package com.gwamcc.aii.web.controller.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gwamcc.aii.forms.LoginForm;

/**
 * 应用功能控制器
 * @author 范大德
 *
 */

@Controller
public class LoginController {
	/*
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response,String key)
			throws ServletException {
		try {
			request.login(loginForm.getUsername(), loginForm.getPassword());
		}
		catch (ServletException authenticationFailed) {
			result.rejectValue(null, "authentication.failed",
					authenticationFailed.getMessage());
			return "login";
		}
		return "redirect:/";
	}*/

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response,LoginForm loginForm, BindingResult result)
			throws ServletException {
		try {
			request.login(loginForm.getUsername(), loginForm.getPassword());
		}
		catch (ServletException authenticationFailed) {
			result.rejectValue(null, "authentication.failed",
					authenticationFailed.getMessage());
			return "login";
		}
		return "redirect:/";
	}

}
