package com.infotrends.in.dao;

import java.util.List;
import java.util.Optional;

import com.infotrends.in.model.FollowRequests;
import com.infotrends.in.model.Post;
import com.infotrends.in.model.User;

public interface UsersDao {
	
	void save(User user);
	void update(User user);
	Optional<User> getById(int id);
	List<User> findAll();
	Optional<User> getByUsername(String username);
	User fetchAllPosts(User user);
	User addNewPosts(User user, Post post);
	User addFollower(User user, User follower);
	User getFollows(User user);
	User getFollowers(User user);
	User removeFollower(User user, int followerId);
	User getFollowRequestsToMe(User user);
	User getFollowRequestsByMe(User user);
	User createFollowRequest(User user, FollowRequests request);
	
}
