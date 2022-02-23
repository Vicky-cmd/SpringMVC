package com.infotrends.in.dao;

import java.util.Optional;

import com.infotrends.in.model.Post;

public interface PostsDao {

	void save(Post post);
	void update(Post post);
	Optional<Post> findById(int postId);
}
