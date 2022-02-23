package com.infotrends.in.dao;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infotrends.in.model.Post;
import com.infotrends.in.utils.HiberbateUtils;

@Repository
public class PostsDaoImpl implements PostsDao{

	@Autowired
	private HiberbateUtils hiberbateUtils;

	@Override
	public void save(Post post) {
		Session session = hiberbateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(post);
		transaction.commit();
		
	}

	@Override
	public void update(Post post) {
		Session session = hiberbateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.update(post);
		transaction.commit();
		
	}

	@Override
	public Optional<Post> findById(int postId) {
		Session session = hiberbateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Optional<Post> post = Optional.of(session.get(Post.class, postId));
		transaction.commit();
		return post;
	}

}
