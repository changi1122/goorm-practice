package demo.reactchatspring.controller;

import demo.reactchatspring.controller.dto.PushSubscribeRequest;
import demo.reactchatspring.service.PushNotificationService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;

    @PostMapping("/subscribe")
    public void subscribe(@RequestHeader(value = "User-Agent", required = false) String ua,
                          PushSubscribeRequest request) {
        // 장치 식별용 이름 설정
        request.setDeviceName(parseDeviceName(ua));
        request.setCreatedAt(LocalDateTime.now());

        pushNotificationService.subscribe(request);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribe(PushSubscribeRequest request) {
        pushNotificationService.unsubscribe(request);
    }

    private String parseDeviceName(String userAgentString) {
        if (!userAgentString.isBlank()) {
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            String browser = userAgent.getBrowser().getName();
            String os = userAgent.getOperatingSystem().getName();
            return browser + " on " + os;
        }
        else {
            return "Unknown";
        }
    }
}
