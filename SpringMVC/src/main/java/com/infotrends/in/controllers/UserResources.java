package com.infotrends.in.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infotrends.in.business.UserProcessor;
import com.infotrends.in.dtos.FollowResponse;
import com.infotrends.in.dtos.PostRequest;
import com.infotrends.in.dtos.PostResponse;
import com.infotrends.in.dtos.UserDetailsRequest;
import com.infotrends.in.dtos.UsersDetailsResponse;
import com.infotrends.in.dtos.UsersRequest;
import com.infotrends.in.dtos.UsersResponse;
import com.infotrends.in.exceptions.BusinessExceptions;
import com.infotrends.in.model.User;
import com.infotrends.in.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserResources {

	@Autowired
	private UserProcessor userProcessor;
	
	@RequestMapping(value = "", method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UsersResponse> createNewUser(@RequestBody @Valid UsersRequest userRequest) throws BusinessExceptions {
		System.out.println(userRequest);
		return userProcessor.createUser(userRequest);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UsersResponse> findAllUsers() throws BusinessExceptions {
		return userProcessor.getAllUsers();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UsersResponse> findByUserId(@PathVariable("userId") int userId) throws BusinessExceptions {
		System.out.println(userId);
		return userProcessor.getUserById(userId);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UsersResponse> updateUser(@PathVariable("userId") int userId) throws BusinessExceptions {
		System.out.println(userId);
		return userProcessor.getUserById(userId);
	}
	

	
	@RequestMapping(value = "/{userId}/updateUserDetails", method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UsersDetailsResponse> addUserDetails(@PathVariable("userId") int userId, @RequestBody @Valid UserDetailsRequest userDetailsRequest) throws BusinessExceptions {
		userDetailsRequest.setUserId(userId);
		System.out.println(userDetailsRequest);
		return userProcessor.addUserDetails(userDetailsRequest);
	}
	
	
	@RequestMapping(value = "/{userId}/posts/addPost", method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PostResponse> addPost(@PathVariable("userId") int userId, @RequestBody @Valid PostRequest postRequest) throws BusinessExceptions {
		postRequest.setAuthor(userId);
		System.out.println(postRequest);
		return userProcessor.addNewPost(postRequest);
	}
	
	
	@RequestMapping(value = "/{userId}/posts/editPost/{postId}", method = RequestMethod.PUT,
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PostResponse> editPost(@PathVariable("userId") int userId, @PathVariable("postId") int postId, @RequestBody @Valid PostRequest postRequest) throws BusinessExceptions {
		postRequest.setAuthor(userId);
		System.out.println(postRequest);
		return userProcessor.editPost(postId, postRequest);
	}

	
	@RequestMapping(value = "/{userId}/posts/editPost/{postId}", method = RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PostResponse> deletePost(@PathVariable("userId") int userId, @PathVariable("postId") int postId) throws BusinessExceptions {
		return userProcessor.deletePost(postId);
	}

	@RequestMapping(value = "/{userId}/posts", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PostResponse> getAllPosts(@PathVariable("userId") int userId) throws BusinessExceptions {
		return userProcessor.getAllPosts(userId);
	}
	
	@RequestMapping(value = "/{userId}/posts/{postId}", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PostResponse> getPostById(@PathVariable("userId") int userId, @PathVariable("postId") int postId) throws BusinessExceptions {
		return userProcessor.getPostById(postId);
	}
	
	@RequestMapping(value = "/{userId}/follow/{followId}", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<FollowResponse> followUserId(@PathVariable("userId") int userId, @PathVariable("followId") int followId) throws BusinessExceptions {
		return userProcessor.followUser(userId, followId);
	}
	
	@RequestMapping(value = "/{userId}/followDetails", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<FollowResponse> getfollowsDetailsForUserId(@PathVariable("userId") int userId) throws BusinessExceptions {
		return userProcessor.getAllFollowDetailsForUser(userId);
	}
	
	@RequestMapping(value = "/{userId}/unfollow/{followId}", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<FollowResponse> unfollowUserId(@PathVariable("userId") int userId, @PathVariable("followId") int followId) throws BusinessExceptions {
		return userProcessor.followUser(userId, followId);
	}
	
}
