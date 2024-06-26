package com.app.chat.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topics/public")
	public ChatMessages sendMessage(@Payload ChatMessages chatMessage) {
		
		return chatMessage;
		
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/topics/public")
	public ChatMessages addUser(@Payload ChatMessages chatMessage,SimpMessageHeaderAccessor headerAccessor)
	{
		
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		
		return chatMessage;
	}
	
}

