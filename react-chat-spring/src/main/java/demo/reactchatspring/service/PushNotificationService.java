package demo.reactchatspring.service;

import demo.reactchatspring.controller.dto.PushSubscribeRequest;
import demo.reactchatspring.entity.PushSubscription;
import demo.reactchatspring.repository.PushSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PushNotificationService {

    private final PushSubscriptionRepository pushSubscriptionRepository;

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

}
