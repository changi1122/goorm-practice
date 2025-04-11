package databaseorm.domain.user;


import jakarta.persistence.*;
import databaseorm.domain.common.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public User(String loginId, String password, String username, UserRole userRole, LocalDateTime createdAt) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.userRole = userRole;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User) || ((User) obj).loginId == null)
            return false;

        return ((User) obj).loginId.equals(this.loginId);
    }

    @Override
    public String toString() {
        return "User{" +
                "loginId='" + loginId + '\'' +
                ", username='" + username + '\'' +
                ", userRole=" + userRole +
                ", createdAt=" + createdAt +
                '}';
    }
}
