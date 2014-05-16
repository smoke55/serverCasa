package it.lucatogno.servercasa.controller;

import it.lucatogno.servercasa.messaggistica.ArduinoMex;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
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
	public ModelAndView vistaMessaggi(HttpServletRequest  request) {
		RestTemplate template = new RestTemplate();
		String url = "http://" + request.getServerName() + ":"+ request.getServerPort() + "/" + request.getContextPath();
		logger.info(url);
		@SuppressWarnings("unchecked")
		List<ArduinoMex> lista = template.getForObject(
				url+"/messaggi", List.class);
		ModelAndView view = new ModelAndView("viewMessaggi");
		view.addObject("mexList", lista);
		return view;
	}

//	private ArduinoMex converterMex(LinkedHashMap mappa) {
//		ArduinoMex mex = new ArduinoMex();
//		mex.setId((Long) mappa.get("id"));
//		mex.setData((Date) mappa.get("data"));
//		mex.setAuthor((String) mappa.get("author"));
//		mex.setContent((String) mappa.get("content"));
//		mex.setCommand((String) mappa.get("command"));
//		mex.setDestinatario((String) mappa.get("destinatario"));
//		return mex;
//	}

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

}
