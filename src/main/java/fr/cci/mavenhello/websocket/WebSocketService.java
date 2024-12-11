package fr.cci.mavenhello.websocket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import fr.cci.mavenhello.websocket.model.InputMessage;

@Component
public class WebSocketService  {

	private WebSocketStompClient webSocketStompClient;
	private MessageStompSessionHandler stompSessionHandler;
	private String URL = "ws://localhost:8080/chat";
	private static WebSocketService instance;	
		
	public static WebSocketService getInsance() {
		if(instance == null) {
			instance = new WebSocketService();
		}
		return instance;
	}
	
	public WebSocketService() {		
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		
		webSocketStompClient = new WebSocketStompClient(webSocketClient);
		webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
		
		stompSessionHandler = new MessageStompSessionHandler();
		webSocketStompClient.connectAsync(URL, stompSessionHandler);
	}
	
	public void subscribe(String path) {
		stompSessionHandler.getSession().subscribe(path, stompSessionHandler);
	}
	
	public void send(InputMessage message) {
		stompSessionHandler.getSession().send("/app/chat", message);
	}
	
	public void close() {
		webSocketStompClient.stop();
	}

}
