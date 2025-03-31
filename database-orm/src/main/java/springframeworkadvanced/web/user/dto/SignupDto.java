package springframeworkadvanced.web.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupDto {

    @NotBlank
    @Size(min = 4, max = 24)
    String loginId;

    @NotBlank
    @Size(min = 4, max = 24)
    String password;

    @NotBlank
    @Size(min = 4, max = 8)
    String username;

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
}
