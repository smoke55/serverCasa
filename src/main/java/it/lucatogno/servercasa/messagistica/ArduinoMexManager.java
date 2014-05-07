package it.lucatogno.servercasa.messagistica;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArduinoMexManager {
	
	@Autowired
	private ArduinoMexQueue arduinoMexQueue;
	
	private Logger logger = Logger.getLogger(getClass());

	public ArduinoMex getLastMex(){
		return arduinoMexQueue.dequeue();
	}
	
	public List<ArduinoMex> getMex(){
		return arduinoMexQueue.getAll();
	}
	
	public void deleteMex(Long id){
		ArduinoMex mex = getMex(id);
		arduinoMexQueue.deleteMex(mex);
	}
	
	public void updateMex(ArduinoMex mex) throws InterruptedException{
		arduinoMexQueue.updateMex(mex);
	}
	
	public ArduinoMex getMex(Long id){
		return arduinoMexQueue.findById(id); 
	}
	
	public void setMex(ArduinoMex mex) throws InterruptedException{
		if(mex.getId() == null){
			mex.setId(generateId());
		}
		arduinoMexQueue.queue(mex);
	}
	
	public Long generateId(){
		Double temp = Math.random()*100000000000000L;
		logger.debug("generato id " + temp.longValue());
		return temp.longValue();
	}
}
