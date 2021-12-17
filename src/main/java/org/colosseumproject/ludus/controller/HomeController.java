package org.colosseumproject.ludus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "Ave Imperator and welcome to the Ludus!";
	}

}
