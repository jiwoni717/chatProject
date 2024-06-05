package com.cp.main;

import java.util.*;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
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
	
	public void handlerTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
		
		String payload = msg.getPayload();
		ChatMessage cmsg = mapper.readValue(payload, ChatMessage.class);
		ChatRoom room = service.findRoomById(cmsg.getRoomId());
		
		Set<WebSocketSession> sessions = room.getSessions();
		
		// 입장, 퇴장시에는 안내 메세지 그 외에는 그냥 입력한 메세지
		if(cmsg.getType().equals(ChatMessage.MessageType.ENTER)) {
			sessions.add(session);
			cmsg.setMsg(cmsg.getSender()+ "님이 입장하였습니다.");
			sendToEachSocket(sessions, new TextMessage(mapper.writeValueAsBytes(cmsg)));
		}
		else if(cmsg.getType().equals(ChatMessage.MessageType.QUIT)) {
			sessions.remove(session);
			cmsg.setMsg(cmsg.getSender()+"님이 퇴장하였습니다.");
			sendToEachSocket(sessions, new TextMessage(mapper.writeValueAsBytes(cmsg)));
		}
		else {
			sendToEachSocket(sessions, msg);
		}
		
	}
	
	public void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage msg) {
		for (WebSocketSession roomSession : sessions) {
	        try {
	            roomSession.sendMessage(msg);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
	}
}
