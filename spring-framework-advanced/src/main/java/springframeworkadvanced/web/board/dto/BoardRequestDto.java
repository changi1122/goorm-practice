package springframeworkadvanced.web.board.dto;

import springframeworkadvanced.domain.board.Board;

public class BoardRequestDto {

    private String title;
    private String body;
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
