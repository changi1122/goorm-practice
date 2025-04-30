package demo.reactchatspring.controller.dto;

import demo.reactchatspring.entity.PushSubscription;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PushSubscribeRequest {

    private String uuid; // userId 대용
    private String endPoint;
    private String publicKey;
    private String auth;
    private String deviceName;
    private LocalDateTime createdAt;

    @Builder
    public PushSubscribeRequest(String uuid, String endPoint, String publicKey, String auth, String deviceName,
                                LocalDateTime createdAt) {
        this.uuid = uuid;
        this.endPoint = endPoint;
        this.publicKey = publicKey;
        this.auth = auth;
        this.deviceName = deviceName;
        this.createdAt = createdAt;
    }

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
