package it.lucatogno.servercasa.controller;

import java.util.Date;

import it.lucatogno.servercasa.messagistica.ArduinoMex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(value="/index.html", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
 
		model.addAttribute("message", "BENVENUTO");
		return "index";
	}

}
