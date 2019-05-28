package kr.co.pk.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
//인스턴스를 자동으로 생성하도록 해주는 어테이션 
@Component		
public class ChatHandler extends TextWebSocketHandler {
	//클라이언트 들을 저장하기 위한 리스트를 생성 
	private Map<String,WebSocketSession> users=
			new HashMap<>();
	//클라이언트가 접속했을 때 호출되는 메소드 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		//리스트에 클라이언트들을 저장
		users.put(session.getId(), session);
	}
	//클라이언트가 접속해제되었을 때 호출되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session,CloseStatus status) {
		//리스트에서 제거
		users.remove(session.getId());
	}
	//클라이언트가 메시지를 전송했을 때 호출되는 메소드
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//Map을 순회
		//반복문 안에서 예외처리를 하게 되면 효율이 떨어집니다. 
		try {
		for(WebSocketSession s : users.values()) {
			s.sendMessage(new TextMessage(message.getPayload()));
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
