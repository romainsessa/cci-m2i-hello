package fr.cci.mavenhello.websocket;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import fr.cci.mavenhello.websocket.event.MessageDispatcher;
import fr.cci.mavenhello.websocket.model.OutputMessage;

public class MessageStompSessionHandler implements StompSessionHandler {

	private StompSession session;

	public StompSession getSession() {
		return session;
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return OutputMessage.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		OutputMessage msg = (OutputMessage) payload;
		System.out.println("Received : " + msg.getText() + " from : " + msg.getFrom());
		MessageDispatcher.getInstance().dispatch(msg);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.out.println("New session established : " + session.getSessionId());
		this.session = session;
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		System.err.println("Got an exception " + exception.getMessage());
		// exception.printStackTrace();
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		System.err.println("Got an transport error " + exception.getMessage());
		// exception.printStackTrace();
	}

}
