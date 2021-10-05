package org.hdivsamples.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/help")
public class HelpController {

	@RequestMapping
	public String admin(final Model model) {

		return "help";
	}

}