package fr.cci.mavenhello.websocket.event;

import java.util.ArrayList;
import java.util.List;

import fr.cci.mavenhello.websocket.model.OutputMessage;

public class MessageDispatcher {
	
	private static MessageDispatcher instance;
	
	public static MessageDispatcher getInstance() {
		if(instance == null) {
			instance = new MessageDispatcher();
		}
		return instance;
	}
	
	List<IMessageListener> listeners = new ArrayList<IMessageListener>();
	
	public void addListener(IMessageListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(IMessageListener listener) {
		this.listeners.remove(listener);
	}
	
	public void dispatch(OutputMessage message) {
		listeners.forEach(listener -> listener.handle(message));
	}

}
