package demo.reactchatspring.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class WebPushMessage {

    public String title;
    public String clickTarget;
    public String message;

    @Builder
    public WebPushMessage(String title, String clickTarget, String message) {
        this.title = title;
        this.clickTarget = clickTarget;
        this.message = message;
    }
}
