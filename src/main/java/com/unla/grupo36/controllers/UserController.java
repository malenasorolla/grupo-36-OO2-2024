package com.unla.grupo36.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo36.dtos.UserDTO;
import com.unla.grupo36.helpers.ViewRouteHelper;

@Controller
public class UserController {

	public UserController() {

	};

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}

	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
	}

	@GetMapping("/register")
	public String create(@ModelAttribute("user") UserDTO user) {
		return ViewRouteHelper.USER_REGISTER;
	}

}
