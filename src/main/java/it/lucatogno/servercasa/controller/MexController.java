package it.lucatogno.servercasa.controller;

import java.util.Date;

import it.lucatogno.servercasa.messagistica.ArduinoMex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Messaggi")
public class MexController {
	
	@RequestMapping(value="/putMessage.do", method = RequestMethod.PUT)
	public String sendMexPage(ModelMap model) {
		ArduinoMex arduinoMex = new ArduinoMex();
		arduinoMex.setAuthor(System.getProperty("username"));
		arduinoMex.setCommand("mex");
		arduinoMex.setData(new Date());
		model.addAttribute("mex", arduinoMex);
		
		return "sendMex";
	}


}
