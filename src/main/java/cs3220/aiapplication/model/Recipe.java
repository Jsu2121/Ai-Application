package cs3220.aiapplication.model;

import java.time.LocalTime;

public class Recipe {
    private int id;
    private int userId;
    private String title;
    private LocalTime date;
    private String prompt;
    private String content;
    private boolean favorite;

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }

    public Recipe(int id, int userId, String title, LocalTime date, String prompt, String content, boolean favorite) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.prompt = prompt;
        this.content = content;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
