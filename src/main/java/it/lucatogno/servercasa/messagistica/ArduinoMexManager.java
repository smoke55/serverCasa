package it.lucatogno.servercasa.messagistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArduinoMexManager {
	
	@Autowired
	private ArduinoMexQueue arduinoMexQueue;

	public ArduinoMex getMex(){
		return arduinoMexQueue.dequeue();
	}
	
	
	public void setMex(ArduinoMex mex) throws InterruptedException{
		if(mex.getId()== null){
			mex.setId(generateId());
		}
		arduinoMexQueue.queue(mex);
	}
	
	public Long generateId(){
		Double temp = Math.random()*100000000000000L;
		return temp.longValue();
	}
}
