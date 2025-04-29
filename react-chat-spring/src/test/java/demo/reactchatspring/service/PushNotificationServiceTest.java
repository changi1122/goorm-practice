package demo.reactchatspring.service;

import demo.reactchatspring.IntegrationTestSupport;
import demo.reactchatspring.controller.dto.PushSubscribeRequest;
import demo.reactchatspring.entity.PushSubscription;
import demo.reactchatspring.repository.PushSubscriptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PushNotificationServiceTest extends IntegrationTestSupport {

    @Autowired
    PushNotificationService pushNotificationService;
    @Autowired
    PushSubscriptionRepository pushSubscriptionRepository;

    @DisplayName("웹 푸시 구독 요청을 저장합니다.")
    @Test
    void subscribe() {
        // given
        String endPoint = "https://fcm.googleapis.com/fcm/send/cx1Ykq8bSxE:APA91bF3YX98k0p-EXAMPLE_END_POINT";
        PushSubscribeRequest request = createPushSubscribeRequest(endPoint);

        // when
        pushNotificationService.subscribe(request);

        // then
        PushSubscription subscription = pushSubscriptionRepository.findByEndPoint(endPoint).orElseThrow();
        assertThat(subscription)
                .usingRecursiveComparison()
                .ignoringFields("subscriptionId", "createdAt","lastUsedAt")
                .isEqualTo(request.toEntity());
    }

    @DisplayName("웹 푸시 구독 정보를 삭제합니다.")
    @Test
    void unsubscribe() {
        // given
        String endPoint = "https://fcm.googleapis.com/fcm/send/cx1Ykq8bSxE:APA91bF3YX98k0p-EXAMPLE_END_POINT";
        PushSubscribeRequest request = createPushSubscribeRequest(endPoint);
        PushSubscription saved = pushNotificationService.subscribe(request);

        // when
        pushNotificationService.unsubscribe(request);

        // then
        Optional<PushSubscription> opSubscription = pushSubscriptionRepository.findById(saved.getSubscriptionId());
        assertThat(opSubscription.isPresent()).isEqualTo(false);
    }

    private PushSubscribeRequest createPushSubscribeRequest(String endPoint) {
        return PushSubscribeRequest.builder()
                .uuid("550e8400-e29b-41d4-a716-446655440000")
                .endPoint(endPoint)
                .publicKey("BBOgkXZpGjNfJkoOvJkdc8v5sDXYZJvV4eGxGgx9TEXAMPLE_PUBLIC_KEY")
                .auth("m2s7GdXEXAMPLE_AUTH")
                .deviceName("Chrome on Windows")
                .createdAt(LocalDateTime.now())
                .build();
    }
}