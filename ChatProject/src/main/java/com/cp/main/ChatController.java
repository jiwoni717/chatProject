package com.cp.main;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

	private final ChatService service;
	
	@RequestMapping("/chat/chatList")
	public String chatList(Model model) {
		List<ChatRoom> list = service.findAllRoom();
		
		model.addAttribute("roomList", list);
		return "chat/chatList";
	}
	
	// 방 만들기
	@PostMapping("chat/createRoom")
	public String createRoom(Model model, @RequestParam String name, String username) {
		ChatRoom room = service.createRoom(name);
		model.addAttribute("room", room);
		model.addAttribute("username", username);
		return "chat/chatRoom";
	}
	
	@GetMapping("/chat/chatRoom")
	public String chatRoom(Model model, @RequestParam String roomId) {
		ChatRoom room = service.findRoomById(roomId);
		model.addAttribute("room", room);
		return "chat/chatRoom";
	}
}
