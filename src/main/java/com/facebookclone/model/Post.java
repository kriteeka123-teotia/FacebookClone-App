package com.facebookclone.model;

public class Post {
	private int postId;
    private int userId;
    private String content;

    // Constructors, Getters, and Setters
    public Post() {}

    public Post(int postId, int userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

}
