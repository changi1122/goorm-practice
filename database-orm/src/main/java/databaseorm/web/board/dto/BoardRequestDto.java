package databaseorm.web.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import databaseorm.domain.board.Board;

public class BoardRequestDto {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String body;

    @NotBlank
    @Size(max = 10)
    private String category;

    public BoardRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Board toEntity() {
        Board post = new Board();
        post.setTitle(title);
        post.setBody(body);
        post.setCategory(category);
        return post;
    }
}
