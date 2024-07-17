package com.example.springboot_server.api.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Post {
    private int id;
    private int authorId;
    private String title;
    private String media;
    private ArrayList<Integer> likes;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Post(int id, int authorId, String title, String media, ArrayList<Integer> likes) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.media = media;
        this.likes = likes;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getMedia() {
        return media;
    }

    public ArrayList<Integer> getLikes() {
        return likes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public void setLikes(ArrayList<Integer> likes) {
        this.likes = likes;
    }
}
