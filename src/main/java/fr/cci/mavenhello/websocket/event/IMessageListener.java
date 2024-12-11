package fr.cci.mavenhello.websocket.event;

import fr.cci.mavenhello.websocket.model.OutputMessage;

public interface IMessageListener {
	
	public void handle(OutputMessage message);
	
}
