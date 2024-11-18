package com.facebookclone.dao;
import com.facebookclone.model.Post;

import java.util.ArrayList;
import java.util.List;
public class PostDAO {
	private List<Post> posts = new ArrayList<>();

    public List<Post> getAllPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

}
