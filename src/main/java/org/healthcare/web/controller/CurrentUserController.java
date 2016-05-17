package org.healthcare.web.controller;

import org.healthcare.config.security.auth.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//@ControllerAdvice
public class CurrentUserController {
	@ModelAttribute("currentUser")
	public CurrentUser getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser){
		return currentUser == null?null:currentUser;
	}
}
