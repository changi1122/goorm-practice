package demo.reactchatspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class PushSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;
    private String uuid; // userId 대용
    @Column(length = 1000)
    private String endPoint;
    @Column(length = 500)
    private String publicKey;
    @Column(length = 500)
    private String auth;
    private String deviceName;
    private LocalDateTime createdAt;
    private LocalDateTime lastUsedAt;

    @Builder
    public PushSubscription(Long subscriptionId, String uuid, String endPoint, String publicKey, String auth,
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
