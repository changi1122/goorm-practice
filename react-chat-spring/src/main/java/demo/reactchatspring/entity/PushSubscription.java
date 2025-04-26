package demo.reactchatspring.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class PushSubscription {

    private Long subscriptionId;
    private Long uuid; // userId 대용
    private String endPoint;
    private String publicKey;
    private String auth;
    private String deviceName;
    private LocalDateTime createdAt;
    private LocalDateTime lastUsedAt;

    @Builder
    public PushSubscription(Long subscriptionId, Long uuid, String endPoint, String publicKey, String auth,
                            String deviceName, LocalDateTime createdAt, LocalDateTime lastUsedAt) {
        this.subscriptionId = subscriptionId;
        this.uuid = uuid;
        this.endPoint = endPoint;
        this.publicKey = publicKey;
        this.auth = auth;
        this.deviceName = deviceName;
        this.createdAt = createdAt;
        this.lastUsedAt = lastUsedAt;
    }
}
