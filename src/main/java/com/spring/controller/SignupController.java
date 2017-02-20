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

//@RequestMapping(value = "/signup")
@Controller
@Scope("session")
public class SignupController {

	@RequestMapping(value = "/signup-step1", method = RequestMethod.GET)
	public String init(Model model) {
		return "/signup-step1";
	}

	@RequestMapping(value = "/signup-step1", method = RequestMethod.POST)
	public ModelAndView step1(HttpServletRequest request, Model model, @ModelAttribute("Person") Person person) {

		request.getSession().setAttribute("error", "");

		if (person != null) {
			// Check for username
			Map<String, Person> persons = (Map<String, Person>) request.getSession().getAttribute("persons");

			if (persons == null) {
				persons = new HashMap<String, Person>();
			}

			if (person.getUserName() == null || person.getUserName().isEmpty()) {
				// Error
				request.getSession().setAttribute("error", "Username cannot be null");
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

	@RequestMapping(value = "/signup-step2", method = RequestMethod.GET)
	public String init2(Model model) {
		return "/signup-step2";
	}

	@RequestMapping(value = "/signup-step2", method = RequestMethod.POST)
	public ModelAndView step2(HttpServletRequest request, Model model, @ModelAttribute("Person") Person person) {

		request.getSession().setAttribute("error", "");

		Person currentPerson = (Person) request.getSession().getAttribute("currentPerson");

		if (currentPerson == null) {
			return new ModelAndView("redirect:/signup-step1");
		}

		if (person.getAge() == null || person.getAge().isEmpty() || person.getColor() == null || person.getColor().isEmpty()
				|| person.getPetName() == null || person.getPetName().isEmpty()) {
			request.getSession().setAttribute("error", "All fields are required");
			return new ModelAndView("redirect:/signup-step2");
		}

		// Check for username
		Map<String, Person> persons = (Map<String, Person>) request.getSession().getAttribute("persons");

		Person oldPerson = persons.get(currentPerson.getUserName());

		if (oldPerson != null) {
			oldPerson.setAge(person.getAge());
			oldPerson.setColor(person.getColor());
			oldPerson.setPetName(person.getPetName());

			request.getSession().setAttribute("persons", persons);
			request.getSession().setAttribute("currentPerson", null);

			return new ModelAndView("redirect:/main");
		}

		request.getSession().setAttribute("error", "Unable to save person");

		return new ModelAndView("redirect:/signup-step2");
	}
}
