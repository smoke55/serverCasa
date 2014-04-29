package it.lucatogno.servercasa.messagistica;

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
	
}
