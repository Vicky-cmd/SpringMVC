package com.infotrends.in.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.infotrends.in.model.FollowRequests;
import com.infotrends.in.model.Post;
import com.infotrends.in.model.User;
import com.infotrends.in.utils.HiberbateUtils;

@Repository
public class UsersDaoImpl implements UsersDao{

	@Autowired
	private HiberbateUtils hiberbateUtils;
	
	@Override
	public void save(User user) {
		Session session = hiberbateUtils.getCurrentSession();
		session.save(user);
	}
	
	@Override
	public void update(User user) {
		Session session = hiberbateUtils.getCurrentSession();
		session.update(user);
	}

	@Override
	public Optional<User> getById(int id) {
		Session session = hiberbateUtils.getCurrentSession();
		return  Optional.of(session.get(User.class, id));
	}

	@Override
	public Optional<User> getByUsername(String username) {
		Session session = hiberbateUtils.getCurrentSession();
		String hql = "FROM User U WHERE U.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		
		return query.getResultList().stream().findFirst();
		
	}

	@Override
	public List<User> findAll() {
		Session session = hiberbateUtils.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		
		List<User> usersList = query.getResultList();
		return usersList;
	}

	@Override
	public User fetchAllPosts(User user) {
		Session session = hiberbateUtils.getCurrentSession();
		System.out.println(user.getWrittenPosts());
		return user;
	}
	
	@Override
	public User addNewPosts(User user, Post post) {
		Session session = hiberbateUtils.getCurrentSession();
		List<Post> posts = user.getWrittenPosts();
		posts.add(post);
		user.setWrittenPosts(posts);
		post.setAuthor(user);
		session.save(post);
		
		return user;
	}

	@Override
	public User addFollower(User user, User follower) {
		Session session = hiberbateUtils.getCurrentSession();
		user.addFollower(follower);
		session.update(user);
		
		return user;
	}

	@Override
	public User getFollows(User user) {
		Session session = hiberbateUtils.getCurrentSession();
//		int size = user.getFollows().size();
		Hibernate.initialize(user.getFollows());
		
		return user;
	}

	@Override
	public User getFollowers(User user) {
		Session session = hiberbateUtils.getCurrentSession();
//		int size = user.getFollowers().size();
		Hibernate.initialize(user.getFollowers());
		
		return user;
	}

	public User removeFollower(User user, int followerId) {
		Session session = hiberbateUtils.getCurrentSession();
		user.getFollowers().removeIf(follower -> follower.getUserId()==followerId);
		session.update(user);
		
		return user;
	}

	@Override
	public User createFollowRequest(User user, FollowRequests request) {
		Session session = hiberbateUtils.getCurrentSession();
		user.addFollowRequest(request);
		session.update(user);
		return user;
	}

	@Override
	public User getFollowRequestsToMe(User user) {
		Hibernate.initialize(user.getRequestsToMe());
		return user;
	}

	@Override
	public User getFollowRequestsByMe(User user) {
		Hibernate.initialize(user.getRequestsByMe());
		return null;
	}
}
