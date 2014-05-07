package it.lucatogno.servercasa.controller;

import it.lucatogno.servercasa.messagistica.ArduinoMex;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/messaggiView")
public class MexController {

	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView vistaMessaggi() {
		RestTemplate template = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<ArduinoMex> lista = template.getForObject(
				"http://localhost:8080/serverHome/messaggi", List.class);
		ModelAndView view = new ModelAndView("viewMessaggi");
		view.addObject("mexList", lista);
		return view;
	}

	private List<ArduinoMex> prendiMessaggi() {
		RestTemplate template = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<LinkedHashMap> readed = template.getForObject(
				"http://localhost:8080/serverHome/messaggi", List.class);
		List<ArduinoMex> lista = new ArrayList<ArduinoMex>();
		for (LinkedHashMap map : readed) {
			lista.add(converterMex(map));
		}

		return lista;
	}

	private ArduinoMex converterMex(LinkedHashMap mappa) {
		ArduinoMex mex = new ArduinoMex();
		mex.setId((Long) mappa.get("id"));
		mex.setData((Date) mappa.get("data"));
		mex.setAuthor((String) mappa.get("author"));
		mex.setContent((String) mappa.get("content"));
		mex.setCommand((String) mappa.get("command"));
		mex.setDestinatario((String) mappa.get("destinatario"));
		return mex;
	}

	@RequestMapping(value = "/sendMex", method = RequestMethod.GET)
	public ModelAndView nuovoMessaggio() {
		ArduinoMex mex = new ArduinoMex();
		mex.setCommand("mex");
		ModelAndView view = new ModelAndView("sendMex");
		view.addObject("mex", mex);
		return view;
	}

	@RequestMapping(value = "/modificaMex", method = RequestMethod.GET)
	public ModelAndView modificaMessaggio(
			@RequestParam(value = "idMex", required = true) Long id) {
		logger.debug("modificaEnter");
		ModelAndView view = new ModelAndView("modifyMex");
		view.addObject("mex", new ArduinoMex());
		view.addObject("idMex", id);
		return view;

	}

	@RequestMapping(value = "/prova.do", method = RequestMethod.POST)
	public void mememem() {
		RestTemplate template = new RestTemplate();
		ArduinoMex mex = new ArduinoMex();
		mex.setAuthor("sada");
		mex.setContent("contat");
		mex.setDestinatario("destdass");
		mex.setCommand("messaggio");
		String url = "http://localhost:8080/serverHome/messaggi";
		logger.debug("(:->)" + mex);
		template.postForObject(url, mex, ArduinoMex.class);
	}

}
