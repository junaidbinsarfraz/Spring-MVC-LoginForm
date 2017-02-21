package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(HttpServletRequest request, Model model) {
		request.getSession().setAttribute("error", "");
//		request.getSession().setAttribute("loginError", "");
		request.getSession().setAttribute("securityError", "");
		return "main";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "success";
	}

}
