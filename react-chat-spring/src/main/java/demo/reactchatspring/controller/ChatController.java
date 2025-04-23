package demo.reactchatspring.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat") // 클라이언트가 /app/chat 로 메시지 보냄
    @SendTo("/topic/chat") // 처리 결과를 구독자들에게 브로드캐스트
    public ChatMessage send(ChatMessage message) {
        return message;
    }
}
