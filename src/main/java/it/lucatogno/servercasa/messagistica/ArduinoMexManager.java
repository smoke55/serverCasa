package it.lucatogno.servercasa.messagistica;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArduinoMexManager {
	
	@Autowired
	private ArduinoMexQueue arduinoMexQueue;
	
	private Logger logger = LogManager.getLogger(getClass());

	public ArduinoMex getLastMex(){
		return arduinoMexQueue.dequeue();
	}
	
	public List<ArduinoMex> getMex(){
		return arduinoMexQueue.getAll();
	}
	
	public ArduinoMex getMex(Long id){
		return arduinoMexQueue.findById(id); 
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
	
//	public String serializeMex(ArduinoMex mex){
//		try {
//			return jsonMapper.writeValueAsString(mex);
//		} catch (JsonGenerationException e) {
//			logger.error("",e);
//			return "ERRORE1";
//		} catch (JsonMappingException e) {
//			logger.error("",e);
//			return "ERRORE2";
//		} catch (IOException e) {
//			logger.error("",e);
//			return "ERRORE3";
//		}
//	}
}
