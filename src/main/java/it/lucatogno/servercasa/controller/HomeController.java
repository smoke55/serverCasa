package it.lucatogno.servercasa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
 
		model.addAttribute("message", "BENVENUTO");
 
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";
 
	}

}