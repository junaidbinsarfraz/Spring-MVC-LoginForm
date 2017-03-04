package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Person;

/**
 * The SignupController class is use to save the user and do signup
 */
@Controller
@Scope("session")
public class SignupController {

	/**
	 * The init() method is use to initialize the signup process
	 * 
	 * @param model
	 *            Provide the view
	 * @return the page to be redirected
	 */
	@RequestMapping(value = "/signup-step1", method = RequestMethod.GET)
	public String init(Model model) {
		return "/signup-step1";
	}

	/**
	 * The step1() method is use to process the step1 data and check is username
	 * is already exists or not
	 * 
	 * @param request
	 *            has all the request attributes
	 * @param model
	 *            has view data
	 * @param person
	 *            has all info of persono
	 * @return step2 is username is unique else show error on step1
	 */
	@RequestMapping(value = "/signup-step1", method = RequestMethod.POST)
	public ModelAndView step1(HttpServletRequest request, Model model, @ModelAttribute("Person") Person person) {

		request.getSession().setAttribute("error", "");
		request.getSession().setAttribute("loginError", "");

		if (person != null) {
			// Check for username
			Map<String, Person> persons = (Map<String, Person>) request.getSession().getAttribute("persons");

			if (persons == null) {
				persons = new HashMap<String, Person>();
			}

			if (person.getUserName() == null || person.getUserName().isEmpty() || person.getDob() == null
					|| person.getDob().isEmpty()) {
				// Error
				request.getSession().setAttribute("error", "All fields are required");
				return new ModelAndView("redirect:/signup-step1");
			}

			if (persons.get(person.getUserName()) != null) {
				// Error
				request.getSession().setAttribute("error", "Username already exists");
				return new ModelAndView("redirect:/signup-step1");
			}

			persons.put(person.getUserName(), person);

			request.getSession().setAttribute("persons", persons);
			request.getSession().setAttribute("currentPerson", person);

			return new ModelAndView("redirect:/signup-step2");
		}

		return new ModelAndView("redirect:/signup-step1");
	}

	/**
	 * The init2() method is use to initialize step2 of signup process
	 * 
	 * @param model
	 *            view data
	 * @return redirect to step2
	 */
	@RequestMapping(value = "/signup-step2", method = RequestMethod.GET)
	public String init2(Model model) {
		return "/signup-step2";
	}

	/**
	 * The step2() method is use to process step2 data i.e. questions.
	 * 
	 * @param request
	 *            has all the request attributes
	 * @param model
	 *            has view data
	 * @param person
	 *            has all info of persono
	 * @return to main page if questions are saved else return to step2 with
	 *         error
	 */
	@RequestMapping(value = "/signup-step2", method = RequestMethod.POST)
	public ModelAndView step2(HttpServletRequest request, Model model, @ModelAttribute("Person") Person person) {

		request.getSession().setAttribute("error", "");

		Person currentPerson = (Person) request.getSession().getAttribute("currentPerson");

		if (currentPerson == null) {
			return new ModelAndView("redirect:/signup-step1");
		}

		if (person.getAnswer1() == null || person.getAnswer1().isEmpty() || person.getAnswer2() == null
				|| person.getAnswer2().isEmpty() || person.getAnswer3() == null || person.getAnswer3().isEmpty()) {
			request.getSession().setAttribute("error", "All fields are required");
			return new ModelAndView("redirect:/signup-step2");
		}

		// Check for username
		Map<String, Person> persons = (Map<String, Person>) request.getSession().getAttribute("persons");

		Person oldPerson = persons.get(currentPerson.getUserName());

		if (oldPerson != null) {
			oldPerson.setAnswer1(person.getAnswer1());
			oldPerson.setAnswer2(person.getAnswer2());
			oldPerson.setAnswer3(person.getAnswer3());
			oldPerson.setQuestion1(person.getQuestion1());
			oldPerson.setQuestion2(person.getQuestion2());
			oldPerson.setQuestion3(person.getQuestion3());

			request.getSession().setAttribute("persons", persons);
			request.getSession().setAttribute("currentPerson", null);

			return new ModelAndView("redirect:/");
		}

		request.getSession().setAttribute("error", "Unable to save person");

		return new ModelAndView("redirect:/signup-step2");
	}
}
