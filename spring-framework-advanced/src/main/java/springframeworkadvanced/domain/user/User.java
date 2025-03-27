package springframeworkadvanced.domain.user;


import springframeworkadvanced.domain.common.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public class User {

    private String loginId;
    private String password;
    private String username;
    private UserRole userRole;
    private LocalDateTime createdAt;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoleList() {
        return List.of(userRole.getRole());
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
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
