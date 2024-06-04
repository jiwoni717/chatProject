package com.cp.main;

import java.util.*;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
	
	private final ObjectMapper mapper;
	private Map<String, ChatRoom> chatRooms;
	
	private void init() {
		chatRooms = new LinkedHashMap<String, ChatRoom>();
	}
	
	public List<ChatRoom> findAllRoom() {
		return new ArrayList<ChatRoom>(chatRooms.values());
	}
	
	public ChatRoom findRoomById(String roomId) {
		return chatRooms.get(roomId);
	}
	
	public ChatRoom createRoom(String name) {
		String randomId = UUID.randomUUID().toString();
		ChatRoom chatRoom = ChatRoom.builder()
				.roomId(randomId)
				.name(name)
				.build();
		chatRooms.put(randomId, chatRoom);
		
		return chatRoom;
	}
}
