package it.lucatogno.servercasa.messagistica.webservice;

import it.lucatogno.servercasa.messagistica.ArduinoMex;
import it.lucatogno.servercasa.messagistica.ArduinoMexManager;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/messaggi")
public class WebServiceArduinoMex {

	@Autowired
	private ArduinoMexManager manager;

	@Autowired
	private MappingJackson2HttpMessageConverter jsonMessageConverter;

	private Logger logger = Logger.getLogger(getClass());

	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	String scriviArduinoMex(@RequestBody ArduinoMex mex) {
		try {
			logger.debug("sto creando " + mex);
			if (mex.getData() == null) {
				mex.setData(new Date());
			}
			manager.setMex(mex);
			logger.info("creato messaggio id->" + mex.getId());
			return "OK";
		} catch (InterruptedException e) {
			logger.error("eccezione sul web service");
			return "ERROR";
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<ArduinoMex> leggiArduinoMexs() {
		List<ArduinoMex> toReturn = manager.getMex();
		return toReturn;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ArduinoMex leggiArduinoMex(@PathVariable Long id) {
		return manager.getMex(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	String aggiornaArduinoMex(@RequestBody ArduinoMex mex) throws InterruptedException {
		if (mex != null && mex.getId() != null) {
			manager.updateMex(mex);
			return "OK";
		}else{
			return "ERROR";
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody
	String cancellaArduinoMex(
			@RequestParam(value = "id", required = true) Long id) {
		manager.deleteMex(id);
		return "OK";
	}

}
