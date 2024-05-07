package com.app.chat.config;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.app.chat.chat.ChatMessages;
import com.app.chat.chat.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
	
	private final SimpMessageSendingOperations messageTemplate;
	
	public void handleWebSocketDisableListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		
		String username = (String)headerAccessor.getSessionAttributes().get("username");
		
		if(username != null) {
			log.info("Uesr Disconnected :{}",username);
			var chatMessage = ChatMessages.builder()
					.type(MessageType.LEAVE)
					.sender(username)
					.build();
			
			messageTemplate.convertAndSend("/topics/public",chatMessage);
			
		}
		
	}

}
