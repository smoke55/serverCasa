package it.lucatogno.servercasa.controller;

import it.lucatogno.servercasa.messagistica.ArduinoMex;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/messaggiView")
public class MexController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView vistaMessaggi() {
		RestTemplate template = new RestTemplate();
		List<ArduinoMex> lista = template.getForObject(
				"http://localhost:8080/serverHome/messaggi", List.class);
		ModelAndView view = new ModelAndView("viewMessaggi");
		view.addObject("mexList", lista);
		return view;
	}

	@RequestMapping(value = "/sendMex", method = RequestMethod.GET)
	public ModelAndView nuovoMessaggio() {
		ArduinoMex mex = new ArduinoMex();
		mex.setCommand("mex");
		ModelAndView view = new ModelAndView("sendMex");
		view.addObject("MexForm", mex);
		return view;
	}

}
