package it.lucatogno.servercasa.messagistica;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

@Service
public class ArduinoMexQueue {
	
	private LinkedBlockingQueue<ArduinoMex> queue;

	public ArduinoMexQueue() {
		this.queue = new LinkedBlockingQueue<ArduinoMex>();
	}
	
	public ArduinoMex dequeue(){
		return queue.poll();
	}
	
	public void queue(ArduinoMex mex) throws InterruptedException{
		queue.put(mex);
	}
	
	public ArduinoMex findById(Long id){
		ArduinoMex mex = new ArduinoMex();
		mex.setId(id);
		if(queue.contains(mex)){
			for(ArduinoMex messaggio : queue){
				if(messaggio.equals(mex)){
					return messaggio;
				}
			}
		}
		return null;
	}
	
	public List<ArduinoMex> getAll(){
//		LinkedBlockingQueue<ArduinoMex> queueClone = new LinkedBlockingQueue<ArduinoMex>(queue);
		List<ArduinoMex> toReturn = new ArrayList<ArduinoMex>();
		for(ArduinoMex mex : queue){
			toReturn.add(mex);
		}
		return toReturn;
	}
	
	public void deleteMex(ArduinoMex mex){
		queue.remove(mex);
	}
	
	public void updateMex(ArduinoMex mex) throws InterruptedException{
		deleteMex(mex);
		queue(mex);
	}
	
}
