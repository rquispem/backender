package com.glovoapp.backender.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	private final String welcomeMessage;

	public WelcomeController(@Value("${backender.welcome_message}") String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@GetMapping("/")
	@ResponseBody
	String root() {
		return welcomeMessage;
	}
}
