package org.healthcare.web.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

@Controller
@SessionAttributes({"key","page"})
public class HealthcareWebController {
	@RequestMapping(value = "/doctor/{id}")
	public String doctorView(@PathVariable("id") Long id, Model model) {
		model.addAttribute("doctorId", id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("isAuthenticated", !auth.getPrincipal().toString().startsWith("anonymous"));
		/*
		if (auth.getPrincipal().toString().startsWith("anonymous")) {
			return "healthcare/login";
		}*/
		return "healthcare/doctor/view";
	}

	@RequestMapping(value = "/doctor-edit/{id}")
	public String doctorEdit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("doctorId", id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal().toString().startsWith("anonymous")) {
			return "healthcare/login";
		}

		return "healthcare/doctor/edit";
	}

	@RequestMapping(value = "/doctor-search-simple")
	public ModelAndView searchPage(@RequestParam("key") String key, @RequestParam("page") Integer page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("searchType", "simple");
		modelAndView.addObject("key", key);
		modelAndView.addObject("page", page);

		modelAndView.setViewName("healthcare/search/search");

		return modelAndView;
	}

	@RequestMapping(value = "/doctor-search-advance")
	public String searchPage(@RequestParam("specializations") String specializations,
			@RequestParam("cities") String cities,@RequestParam("rate") Integer rate, @RequestParam("page") Integer page, Model model) {
		Set<String> spTemp = new HashSet<String>(Arrays.asList(specializations.split(",")));

		model.addAttribute("searchType", "advance");
		model.addAttribute("specializations", StringUtils.join(spTemp, ","));
		model.addAttribute("cities", cities);
		model.addAttribute("rate", rate);
		model.addAttribute("page", page);
		
		ModelAndView modelAndView = new ModelAndView();
		

		return "healthcare/search/search";
	}
}
