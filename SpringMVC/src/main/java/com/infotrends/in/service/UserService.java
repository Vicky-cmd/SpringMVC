package com.infotrends.in.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.infotrends.in.dao.UsersDao;
import com.infotrends.in.exceptions.BusinessExceptions;
import com.infotrends.in.model.FollowRequests;
import com.infotrends.in.model.Post;
import com.infotrends.in.model.User;
import com.infotrends.in.utils.EntityType;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UsersDao usersDao;
	
	public void save(User user) throws BusinessExceptions {
		if(usersDao.getByUsername(user.getUsername()).isPresent()) {
//			throw new BusinessExceptions("AAAAAAAAAAAAAAAAAAAAAAAAAAH!");
			throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
							  .message("Duplicate_Entry")
							  .details("Username Already Exists!")
							  .build();
		}
		
		user.setCreatedBy("SYSTEM");
		user.setModifiedBy("SYSTEM");
		user.setCreatedOn(LocalDateTime.now());
		user.setModifiedOn(LocalDateTime.now());
		usersDao.save(user);
	}

	public void update(User user) throws BusinessExceptions {
		
		user.setModifiedBy("SYSTEM");
		user.setModifiedOn(LocalDateTime.now());
		usersDao.update(user);
	}
	

	public Optional<User> getById(int id) throws BusinessExceptions {
		return usersDao.getById(id);

	}
	

	public Optional<User> getByUserName(String username) throws BusinessExceptions {
		return usersDao.getByUsername(username);

	}
	
	public User findById(int id) throws BusinessExceptions {
		
		Optional<User> user = usersDao.getById(id);
		if(user.isPresent()) return user.get();

		throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
		  .message("Invalid_User_Id")
		  .details("User Not Found in the system")
		  .build();
	}

	public List<User> findAllUsers() {
		return usersDao.findAll();
	}
	
	public User fetchAllPosts(int id) throws BusinessExceptions {
		User user = findById(id);
		return usersDao.fetchAllPosts(user);
	}
	
	public User addNewPosts(int userId, Post post) throws BusinessExceptions {
		return usersDao.addNewPosts(findById(userId), post);
	}
	
	public User addFollower(int userId, int followId) throws BusinessExceptions {
		User user = findById(followId);
		User follower = findById(userId);
		if(getFollowers(user).getFollowers().contains(follower)) {

			throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
			  .message("Invalid_Request")
			  .details("You are already following the user")
			  .build();
		}
		
		if(user.getAccountType().equals(EntityType.PRIVATE)) {
			return createFollowRequest(user, follower);
		}
		
		return usersDao.addFollower(user, follower);
		
	}
	
	private User createFollowRequest(User user, User follower) throws BusinessExceptions {
		
		if(usersDao.getFollowRequestsToMe(user).getRequestsToMe().stream().anyMatch(request -> {
			return request.getRequestor().getUserId()==follower.getUserId() && !(request.getRequestStatus().equalsIgnoreCase("D") || request.getRequestStatus().equalsIgnoreCase("S"));
		})) {
			throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
			  .message("Invalid_Request")
			  .details("Follow Aready Requested")
			  .build();
		}
		
		FollowRequests request = new FollowRequests();
		request.setUser(user);
		request.setRequestor(follower);
		request.setRequestStatus("I");
		request.setCreatedOn(LocalDateTime.now());
		request.setCreatedBy("SYSTEM");
		request.setModifiedOn(LocalDateTime.now());
		request.setModifiedBy("SYSTEM");
		usersDao.createFollowRequest(user, request);
		
		return user;
	}

	public User removeFollower(int userId, int followId) throws BusinessExceptions {
		User user = findById(followId);
		if(!getFollowers(user).getFollowers().stream().anyMatch(follower -> follower.getUserId()==followId)) {

			throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
			  .message("Invalid_Request")
			  .details("Invalid Follower Id")
			  .build();
		}
		
		return usersDao.removeFollower(user, followId);
		
	}
	
	public User getFollows(User user) {
		usersDao.getFollows(user);
		return user;
	}

	public User getFollowers(User user) {
		usersDao.getFollowers(user);
		return user;
	}
	
	public User getAllFollowDetailsForUser(int userId) throws BusinessExceptions {
		User user = findById(userId);
		usersDao.getFollows(user);
		usersDao.getFollowers(user);
		
		return user;
	}
	
}
