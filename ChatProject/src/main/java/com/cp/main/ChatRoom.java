package com.cp.main;

import java.util.*;
import org.springframework.web.socket.WebSocketSession;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {
	
	private String roomId, name;
	private Set<WebSocketSession> sessions = new HashSet<WebSocketSession>();
	
	@Builder
	public ChatRoom(String roomId, String name) {
		this.roomId = roomId;
		this.name = name;
	}
}
