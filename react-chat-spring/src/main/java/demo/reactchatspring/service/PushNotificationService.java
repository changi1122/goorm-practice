package demo.reactchatspring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.reactchatspring.controller.dto.PushSubscribeRequest;
import demo.reactchatspring.entity.PushSubscription;
import demo.reactchatspring.repository.PushSubscriptionRepository;
import demo.reactchatspring.service.dto.WebPushMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PushNotificationService {

    private final PushSubscriptionRepository pushSubscriptionRepository;
    private final PushService pushService;
    private final ObjectMapper objectMapper;

    @Transactional
    public PushSubscription subscribe(PushSubscribeRequest request) {
        PushSubscription subscription = request.toEntity();
        log.info("알림 구독: {}", subscription);
        return pushSubscriptionRepository.save(subscription);
    }

    @Transactional
    public void unsubscribe(PushSubscribeRequest request) {
        PushSubscription subscription =
                pushSubscriptionRepository.findByEndPoint(request.getEndPoint()).orElseThrow();
        pushSubscriptionRepository.delete(subscription);
    }

    @Transactional
    public void sendPushTo(String uuid, WebPushMessage message, LocalDateTime now) {

        List<PushSubscription> subscriptions = pushSubscriptionRepository.findByUuid(uuid);
        if (subscriptions.isEmpty())
            throw new RuntimeException("");

        for (PushSubscription subscription : subscriptions) {
            try {
                Notification notification = new Notification(
                        subscription.getEndPoint(),
                        subscription.getPublicKey(),
                        subscription.getAuth(),
                        objectMapper.writeValueAsBytes(message)
                );

                pushService.send(notification);

                subscription.setLastUsedAt(now);
                pushSubscriptionRepository.save(subscription);
            }
            catch (Exception e) {
                log.error("push send error: ", e);
            }
        }
    }
}
