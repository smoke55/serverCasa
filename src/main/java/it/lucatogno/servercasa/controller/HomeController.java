package it.lucatogno.servercasa.controller;

import java.util.Date;

import it.lucatogno.servercasa.messaggistica.ArduinoMex;
import it.lucatogno.servercasa.messaggistica.ArduinoMexManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ArduinoMexManager arduinoMexManager;
		
	@RequestMapping(value="/index.html", method = RequestMethod.GET)
	public String welcome(ModelMap model) throws InterruptedException {
		inserisciMexFittizi();
		return "index";
	}
	
	private void inserisciMexFittizi() throws InterruptedException{
		int nMex= 1;
		for(Integer i = 0 ; i< nMex ; i++ ){
			ArduinoMex mex = new ArduinoMex();
			mex.setAuthor("Autore" + i);
			mex.setCommand("mex");
			mex.setData(new Date());
			mex.setContent(i+ " mex prova "+i);
			mex.setDestinatario("dest "+i);
			mex.setId(i.longValue());
			arduinoMexManager.setMex(mex);
		}
		
	}

}
