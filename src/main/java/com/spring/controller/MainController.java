package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The MainController class is use to manage the main view of the program
 */
@Controller
public class MainController {

	/**
	 * The init() method is use to initialize the main view
	 * 
	 * @param request
	 *            has all the request attributes
	 * @param model
	 *            has view data
	 * @return to the index page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(HttpServletRequest request, Model model) {
		request.getSession().setAttribute("error", "");
		request.getSession().setAttribute("securityError", "");
		return "main";
	}

	/**
	 * The success() method is use to show success page
	 * 
	 * @param request
	 *            has all the request attributes
	 * @param model
	 *            has view data
	 * @param response
	 *            has the response attributes
	 * @return to the success page
	 */
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "success";
	}

}
