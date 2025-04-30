package demo.reactchatspring.controller;

import demo.reactchatspring.service.PushNotificationService;
import demo.reactchatspring.service.dto.WebPushMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final PushNotificationService pushNotificationService;

    @MessageMapping("/chat") // 클라이언트가 /app/chat 로 메시지 보냄
    @SendTo("/topic/chat") // 처리 결과를 구독자들에게 브로드캐스트
    public ChatMessage send(ChatMessage message) {
        pushNotificationService.sendPushToAll(
                WebPushMessage.builder()
                        .title(message.getSender())
                        .message(message.getContent())
                        .clickTarget("localhost:5713")
                        .build(),
                LocalDateTime.now()
        );

        return message;
    }
}
