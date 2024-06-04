package com.cp.main;

import java.util.*;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatHandler extends TextWebSocketHandler {
	
	private final ObjectMapper mapper;
	private final ChatService service;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
	}
	
	@Override
	public void handlerTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
		
		String payload = msg.getPayload();
		ChatMessage cmsg = mapper.readValue(payload, ChatMessage.class);
		
	}
}
