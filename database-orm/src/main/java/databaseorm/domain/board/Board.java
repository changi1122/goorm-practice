package databaseorm.domain.board;

import databaseorm.domain.user.User;

import java.time.LocalDateTime;

public class Board {

    private Long id;
    private String title;
    private String body;
    private String category;
    private User author;

    private LocalDateTime createdAt;
    private LocalDateTime lastEditedAt;

    public Board() {}

    public Board(Long id, String title, String body, String category, User author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.category = category;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(LocalDateTime lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }
}
