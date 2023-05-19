package kz.bitlab.techorda.models;

import java.util.Date;

public class News {
    private int id;
    private Date postDate;
    private NewsCategory newsCategory;
    private String title;
    private String content;
    private int status;

    public News(){

    }

    public News(int id, Date postDate, NewsCategory newsCategory, String title, String content, int status) {
        this.id = id;
        this.postDate = postDate;
        this.newsCategory = newsCategory;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }
}
