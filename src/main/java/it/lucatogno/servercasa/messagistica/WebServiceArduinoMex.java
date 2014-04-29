package it.lucatogno.servercasa.messagistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/messaggi")
public class WebServiceArduinoMex {
	
	@Autowired
	private ArduinoMexManager manager;
	
	@RequestMapping(value="/scrivi", method=RequestMethod.PUT)
	public @ResponseBody HttpStatus scriviArduinoMex(
	            @RequestParam(value="mex", required=true) ArduinoMex mex){
			try {
				manager.setMex(mex);
				return HttpStatus.CREATED;
			} catch (InterruptedException e) {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
	    }
	
	@RequestMapping("/leggi")
	 public @ResponseBody ArduinoMex leggiArduinoMex(
	            @RequestParam(value="id", required=false) Long id) {
	        return manager.getMex();
	    }
	
//	@RequestMapping("/aggiorna")
//	 public @ResponseBody ArduinoMex aggiornaArduinoMex(
//	            @RequestParam(value="id", required=true) Long id) {
//	        return new Greeting(counter.incrementAndGet(),
//	                            String.format(template, id));
//	    }
//	
//	@RequestMapping("/cancella")
//	 public @ResponseBody ArduinoMex cancellaArduinoMex(
//	            @RequestParam(value="id", required=true) Long id) {
//	        return new Greeting(counter.incrementAndGet(),
//	                            String.format(template, id));
//	    }

}
