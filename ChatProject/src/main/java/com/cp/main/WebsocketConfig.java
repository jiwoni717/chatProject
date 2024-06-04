package com.cp.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

/*
 	handler를 이용하여 WebSocket을 활성화하기 위한 Config 파일
 	@EnableWebSocket을 선언하여 WebSocket을 활성화 시킨다
 */

@RequiredArgsConstructor
@Configuration
@EnableWebSocket

public class WebsocketConfig {

	private final WebSocketHandler webSocketHandler;
	
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
	}//                                      -----------→ WebSocket에 접속하기 위한 endpoint
}
