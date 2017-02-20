package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Person;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String init(Model model) {
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest request, Model model, @ModelAttribute("Person") Person person) {

		// Check for username
		Map<String, Person> persons = (Map<String, Person>) request.getSession().getAttribute("persons");

		if (persons == null) {
			persons = new HashMap<String, Person>();
		}

		if (person.getUserName() == null || person.getUserName().isEmpty()) {
			// Error
			request.getSession().setAttribute("loginError", "Username cannot be null");
			return new ModelAndView("redirect:/");
		}
		
		if (persons.get(person.getUserName()) == null) {
			// Error
			request.getSession().setAttribute("loginError", "Username not exists");
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("redirect:/login");

	}
}
