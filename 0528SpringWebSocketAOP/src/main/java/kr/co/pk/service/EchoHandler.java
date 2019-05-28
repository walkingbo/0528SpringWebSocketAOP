package kr.co.pk.service;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//인스턴스(Bean) 이 자동생성되도록 설정하는 어노테이션
@Component
public class EchoHandler extends TextWebSocketHandler {
	//클라이언트에서 메시지를 보냈을 때 호출되는 메소드 
	@Override //상위 클래스에 없는 메소드 이면 에러 
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//받은 메시지 출력 
		System.out.println(session.getId()+"가"+message.getPayload()+"를 보냄");
		//메시지 전송
		try {
			session.sendMessage(new TextMessage("반사:"+message.getPayload()));
		} catch (Exception e) {
			
		}
	}
}
