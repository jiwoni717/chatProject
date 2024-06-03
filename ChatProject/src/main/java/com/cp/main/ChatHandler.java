package com.cp.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class ChatHandler extends TextWebSocketHandler {
	
	private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws Exception {
		
		// 전송되는 데이터
		String pl = msg.getPayload();
		log.info("payload : "+pl);
		
		for(WebSocketSession sess : list) {
			sess.sendMessage(msg);
		}
	}
	
	// 리스트에 웹소켓 세션들을 저장해 메세지를 각 세션에 뿌려주는 handling
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		log.info(session + "클라이언트 접속");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info(session + "클라이언트 접속 해제");
		list.remove(session);
	}
}
