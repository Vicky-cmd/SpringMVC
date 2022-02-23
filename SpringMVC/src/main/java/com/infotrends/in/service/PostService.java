package com.infotrends.in.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.infotrends.in.dao.PostsDao;
import com.infotrends.in.exceptions.BusinessExceptions;
import com.infotrends.in.model.Post;

@Service
public class PostService {

	@Autowired
	private PostsDao postsDao;
	
	
	public void save(Post post) {
		postsDao.save(post);
	}
	public void update(Post post) {
		postsDao.update(post);
	}
	public Post findById(int postId) throws BusinessExceptions{
		Optional<Post> post = postsDao.findById(postId);
		if(post.isPresent()) {
			return post.get();
		}
		

		throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
		  .message("Invalid_Post_Id")
		  .details("Post Not Found in the system")
		  .build();
	}
	
}
