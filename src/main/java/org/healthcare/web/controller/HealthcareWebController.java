package org.healthcare.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HealthcareWebController {
	@RequestMapping(value = "/doctor/{id}")
	public String doctorView(@PathVariable("id") Long id, Model model) {
		model.addAttribute("doctorId", id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal().toString().startsWith("anonymous")) {
			return "healthcare/login";
		}
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
	public String searchPage(@RequestParam("key") String key, @RequestParam("page") Integer page, Model model) {
		model.addAttribute("searchType", "simple");
		model.addAttribute("key", key);
		model.addAttribute("page", page);

		return "healthcare/search/search";
	}
	
	@RequestMapping(value = "/doctor-search-advance")
	public String searchPage(@RequestParam("specializations") String specializations,@RequestParam("cities") String cities, @RequestParam("page") Integer page, Model model) {
		model.addAttribute("searchType", "advance");
		model.addAttribute("specializations", specializations);
		model.addAttribute("cities", cities);
		model.addAttribute("page", page);

		return "healthcare/search/search";
	}
}
