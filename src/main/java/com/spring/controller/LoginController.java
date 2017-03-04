package com.spring.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Person;

/**
 * The LoginController class is use to manage login with username and securoty
 * questions of a user
 */
@Controller
public class LoginController {

	/**
	 * The init() method is use to initialize the login steps
	 * 
	 * @param request
	 *            has all the request attributes
	 * @param model
	 *            has view data
	 * @param response
	 *            has the response attributes
	 * @return redirect to login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response, Model model) {

		// Check for username
		Map<String, Person> persons = (Map<String, Person>) request.getSession().getAttribute("persons");

		Person loginPerson = (Person) request.getSession().getAttribute("loginPerson");

		if (loginPerson == null || persons == null) {
			return new ModelAndView("redirect:/");
		}

		// Select one of security question
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((3 - 1) + 1) + 1;

		// Populate security question
		if (randomNum == 1) {
			request.getSession().setAttribute("question", loginPerson.getQuestion1());
			request.getSession().setAttribute("answer", loginPerson.getAnswer1());
		} else if (randomNum == 2) {
			request.getSession().setAttribute("question", loginPerson.getQuestion2());
			request.getSession().setAttribute("answer", loginPerson.getAnswer2());
		} else {
			request.getSession().setAttribute("question", loginPerson.getQuestion3());
			request.getSession().setAttribute("answer", loginPerson.getAnswer3());
		}

		return new ModelAndView("/login");
	}

	/**
	 * The securityAnswer() method is use to verify the security questions and
	 * answers.
	 * 
	 * @param request
	 *            has all the request attributes
	 * @param model
	 *            has view data
	 * @param response
	 *            has the response attributes
	 * @return if verified then return to the success page else show error
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView securityAnswer(HttpServletRequest request, HttpServletResponse response, Model model) {
		// Check response

		String answer = request.getParameter("answer");

		if (answer == null || answer.isEmpty()) {
			request.getSession().setAttribute("securityError", "Answer is required.");

			return new ModelAndView("redirect:/login");
		}

		if (request.getSession().getAttribute("answer").equals(answer)) {
			return new ModelAndView("redirect:/success");
		}

		request.getSession().setAttribute("securityError", "Answer is wrong.");

		return new ModelAndView("redirect:/login");
	}

	/**
	 * The submit() method will be called for username verification
	 * 
	 * @param request
	 * @param model
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest request, Model model, @ModelAttribute("Person") Person person) {

		// Check for username
		Map<String, Person> persons = (Map<String, Person>) request.getSession().getAttribute("persons");

		request.getSession().setAttribute("loginError", "");

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

		request.getSession().setAttribute("loginPerson", persons.get(person.getUserName()));

		return new ModelAndView("redirect:/login");

	}
}
