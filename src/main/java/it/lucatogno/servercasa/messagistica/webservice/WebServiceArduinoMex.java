package it.lucatogno.servercasa.messagistica.webservice;

import java.util.ArrayList;
import java.util.List;

import it.lucatogno.servercasa.messagistica.ArduinoMex;
import it.lucatogno.servercasa.messagistica.ArduinoMexManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/messaggi")
public class WebServiceArduinoMex {

	@Autowired
	private ArduinoMexManager manager;

	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody
	HttpStatus scriviArduinoMex(
			@RequestBody ArduinoMex mex) {
		try {
			manager.setMex(mex);
			return HttpStatus.CREATED;
		} catch (InterruptedException e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<ArduinoMex> leggiArduinoMex(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			List<ArduinoMex> recieved = new ArrayList<ArduinoMex>();
			recieved.add(manager.getMex(id));
			return recieved;
		} else {
			return manager.getMex();
		}
	}

	// @RequestMapping("/aggiorna")
	// public @ResponseBody ArduinoMex aggiornaArduinoMex(
	// @RequestParam(value="id", required=true) Long id) {
	// return new Greeting(counter.incrementAndGet(),
	// String.format(template, id));
	// }
	//
	// @RequestMapping("/cancella")
	// public @ResponseBody ArduinoMex cancellaArduinoMex(
	// @RequestParam(value="id", required=true) Long id) {
	// return new Greeting(counter.incrementAndGet(),
	// String.format(template, id));
	// }

}
