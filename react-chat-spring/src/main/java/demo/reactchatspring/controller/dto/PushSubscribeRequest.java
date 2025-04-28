package demo.reactchatspring.controller.dto;

import demo.reactchatspring.entity.PushSubscription;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class PushSubscribeRequest {

    private String uuid; // userId 대용
    private String endPoint;
    private String publicKey;
    private String auth;
    private String deviceName;
    private LocalDateTime createdAt;

    public PushSubscription toEntity() {
        return PushSubscription.builder()
                .uuid(uuid)
                .endPoint(endPoint)
                .publicKey(publicKey)
                .auth(auth)
                .deviceName(deviceName)
                .createdAt(createdAt)
                .build();
    }
}
