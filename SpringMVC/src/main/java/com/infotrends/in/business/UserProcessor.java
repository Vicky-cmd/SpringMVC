package com.infotrends.in.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotrends.in.dtos.FollowResponse;
import com.infotrends.in.dtos.PostRequest;
import com.infotrends.in.dtos.PostResponse;
import com.infotrends.in.dtos.PostsDto;
import com.infotrends.in.dtos.UserDetailsRequest;
import com.infotrends.in.dtos.UsersDetailsResponse;
import com.infotrends.in.dtos.UsersDto;
import com.infotrends.in.dtos.UsersRequest;
import com.infotrends.in.dtos.UsersResponse;
import com.infotrends.in.exceptions.BusinessExceptions;
import com.infotrends.in.model.Post;
import com.infotrends.in.model.User;
import com.infotrends.in.model.UserDetails;
import com.infotrends.in.service.PostService;
import com.infotrends.in.service.UserService;
import com.infotrends.in.utils.EntityType;
import com.infotrends.in.utils.UserRole;
import com.infotrends.in.utils.UserStatus;
import com.mysql.cj.util.StringUtils;

@Component
public class UserProcessor {

	@Autowired
	private UserService userService;
	
//	@Autowired
	private ObjectMapper mapper = objectMapper();
	
	@Autowired
	private PostService postService;
	
	public ObjectMapper objectMapper() {
		JsonFactory factory = new JsonFactory();
		factory.configure(Feature.IGNORE_UNKNOWN, true);
		
		ObjectMapper objectMapper = new ObjectMapper(factory);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		
		return objectMapper;
	}
	
	public ResponseEntity<UsersResponse> createUser(UsersRequest usersRequest) throws BusinessExceptions {
		UsersResponse response = new UsersResponse();
		User user = mapUserRequestToUser(usersRequest);
		
		userService.save(user);
		response.setStatus(String.valueOf(HttpStatus.CREATED.value()));
		response.setUserId(String.valueOf(user.getUserId()));
		return new ResponseEntity<UsersResponse>(response, HttpStatus.CREATED);
	}
	
	public ResponseEntity<UsersResponse> updateUser(int userId, UsersRequest userRequest) throws BusinessExceptions {
		UsersResponse response = new UsersResponse();
		User user = userService.findById(userId);
		modifyUserDataUsingRequest(user, userRequest);
//		User user = mapUserRequestToUser(usersRequest);
		
		userService.update(user);
		response.setStatus(String.valueOf(HttpStatus.OK.value()));
		response.setUserId(String.valueOf(user.getUserId()));
		return new ResponseEntity<UsersResponse>(response, HttpStatus.OK);
	}
	
	private void modifyUserDataUsingRequest(User user, UsersRequest userRequest) throws BusinessExceptions {
		if(!StringUtils.isNullOrEmpty(userRequest.getUsername())) {
			if(userService.getByUserName(userRequest.getUsername()).isPresent()) {
				throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
				  .message("Invalid_User_Id")
				  .details("Username already Exists")
				  .build();
			}
			user.setUsername(userRequest.getUsername());
		}
		if(!StringUtils.isNullOrEmpty(userRequest.getFullName())) {
			user.setFullName(userRequest.getFullName());
		}
		if(!StringUtils.isNullOrEmpty(userRequest.getPassword())) {
			user.setPassword(userRequest.getPassword());
		}
		if(!StringUtils.isNullOrEmpty(userRequest.getAccountType())) {
			user.setAccountType(EntityType.valueOf(userRequest.getAccountType()));
		}
		if(!StringUtils.isNullOrEmpty(userRequest.getStatus())) {
			user.setStatus(UserStatus.valueOf(userRequest.getStatus()));
		}
		if(!StringUtils.isNullOrEmpty(userRequest.getUserRole())) {
			user.setUserRole(UserRole.valueOf(userRequest.getUserRole()));
		}
		user.setModifiedBy("SYSTEM");
		user.setModifiedOn(LocalDateTime.now());
	}

	public ResponseEntity<UsersResponse> getUserById(int id) throws BusinessExceptions {
		UsersResponse response = new UsersResponse();
		
		User user = userService.findById(id);
		response.setStatus(String.valueOf(HttpStatus.OK.value()));
		response.setUserDetails(mapUserDataToDto(user));
		return new ResponseEntity<UsersResponse>(response, HttpStatus.OK);
		
	}
	
	public ResponseEntity<UsersResponse> getAllUsers() throws BusinessExceptions {
		UsersResponse response = new UsersResponse();
		
		List<User> users = userService.findAllUsers();
		if(users!=null && users.size()>0) {
			response.setStatus(String.valueOf(HttpStatus.OK.value()));
			response.setUserDetailsList(mapUserDataToDto(users));
			return new ResponseEntity<UsersResponse>(response, HttpStatus.OK);
		}
		
		throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
		  .message("Blank_Result")
		  .details("No users found in the system")
		  .build();
	}
	

	public ResponseEntity<UsersDetailsResponse> addUserDetails(UserDetailsRequest usersDetailsRequest) throws BusinessExceptions {
		UsersDetailsResponse response = new UsersDetailsResponse();
		
		
		User user = userService.findById(Integer.valueOf(usersDetailsRequest.getUserId()));
		populateUserDetails(usersDetailsRequest, user);
		
		userService.update(user);
		response.setStatus(String.valueOf(HttpStatus.OK.value()));
		response.setUserId(String.valueOf(user.getUserId()));
		return new ResponseEntity<UsersDetailsResponse>(response, HttpStatus.OK);
	}
	

	public ResponseEntity<PostResponse> addNewPost(PostRequest postRequest) throws BusinessExceptions {
		PostResponse response = new PostResponse();
		
		
//		User user = userService.findById(Integer.valueOf(postRequest.getAuthor()));
//		User user = userService.findById(Integer.valueOf(postRequest.getAuthor()));
		Post newPost = populatePostDetails(postRequest, new Post());//mapPostRequestToPost(postRequest);
		newPost.setPostStatus("N");
		newPost.setCreatedBy("SYSTEM");
		newPost.setCreatedOn(LocalDateTime.now());
		newPost.setModifiedBy("SYSTEM");
		newPost.setModifiedOn(LocalDateTime.now());
		
		userService.addNewPosts(Integer.valueOf(postRequest.getAuthor()), newPost);
		response.setStatus(String.valueOf(HttpStatus.CREATED.value()));
		response.setPostId(String.valueOf(newPost.getPostId()));
		return new ResponseEntity<PostResponse>(response, HttpStatus.CREATED);
	}
	
	public ResponseEntity<PostResponse> editPost(int postId, PostRequest postRequest) throws BusinessExceptions {
		PostResponse response = new PostResponse();
		
		Post post = postService.findById(postId);
		populatePostDetails(postRequest, post);
		
		postService.update(post);
		response.setStatus(String.valueOf(HttpStatus.OK.value()));
		response.setPostId(String.valueOf(post.getPostId()));
		return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<PostResponse> deletePost(int postId) throws BusinessExceptions {
		PostResponse response = new PostResponse();
		
		Post post = postService.findById(postId);
		if(post.getPostStatus().equalsIgnoreCase("Y")) {
			throw BusinessExceptions.builder().status(HttpStatus.BAD_REQUEST)
			  .message("Invalid_Post_Id")
			  .details("Post is already Deleted")
			  .build();
		}
		
		post.setPostStatus("Y");
		
		postService.update(post);
		response.setStatus(String.valueOf(HttpStatus.OK.value()));
		response.setPostId(String.valueOf(post.getPostId()));
		return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<PostResponse> getPostById(int postId) throws BusinessExceptions {
		PostResponse response = new PostResponse();
		
		Post post = postService.findById(postId);
		
		response.setStatus(String.valueOf(HttpStatus.OK.value()));
		response.setPostId(String.valueOf(post.getPostId()));
		response.setPost(mapPostDataToPostsDto(post));
		return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<PostResponse> getAllPosts(int userId) throws BusinessExceptions {
		PostResponse response = new PostResponse();
		
		User user = userService.fetchAllPosts(userId);
		
		response.setStatus(String.valueOf(HttpStatus.OK.value()));
		response.setPosts(mapPostDataToPostsDto(user.getWrittenPosts()));
		return new ResponseEntity<PostResponse>(response, HttpStatus.OK);
	}
	
	private Post populatePostDetails(PostRequest postRequest, Post post) {
		
		if(!StringUtils.isNullOrEmpty(postRequest.getTitle())) {
			post.setTitle(postRequest.getTitle());
		}
		if(!StringUtils.isNullOrEmpty(postRequest.getContent())) {
			post.setContent(postRequest.getContent());
		}
		if(!StringUtils.isNullOrEmpty(postRequest.getPostType())) {
			post.setPostType(EntityType.valueOf(postRequest.getPostType()));
		}
		if(!StringUtils.isNullOrEmpty(postRequest.getAttachments())) {
			post.setAttachments(postRequest.getAttachments());
		}

		post.setModifiedBy("SYSTEM");
		post.setModifiedOn(LocalDateTime.now());
		
		return post;
	}

	private void populateUserDetails(UserDetailsRequest usersDetailsRequest, User user) {
		UserDetails userDetails = null;
		if(user.getUserDetails()==null) {
			userDetails = mapUserDetailsRequest(usersDetailsRequest);

			userDetails.setCreatedBy("SYSTEM");
			userDetails.setCreatedOn(LocalDateTime.now());
		} else {
			userDetails=user.getUserDetails();
			if(usersDetailsRequest.getDateOfBirth()!=null) {
				userDetails.setDateOfBirth(usersDetailsRequest.getDateOfBirth());
			}
			if(!StringUtils.isNullOrEmpty(usersDetailsRequest.getHobby())) {
				userDetails.setHobby(usersDetailsRequest.getHobby());
			}
			if(!StringUtils.isNullOrEmpty(usersDetailsRequest.getProfileLinks())) {
				userDetails.setProfileLinks(usersDetailsRequest.getProfileLinks());
			}
			if(!StringUtils.isNullOrEmpty(usersDetailsRequest.getProfileURI())) {
				userDetails.setProfileURI(usersDetailsRequest.getProfileURI());
			}
			if(!StringUtils.isNullOrEmpty(usersDetailsRequest.getUserBio())) {
				userDetails.setUserBio(usersDetailsRequest.getUserBio());
			}
			if(!StringUtils.isNullOrEmpty(usersDetailsRequest.getOccupation())) {
				userDetails.setOccupation(usersDetailsRequest.getOccupation());
			}
			if(usersDetailsRequest.getRecieveEmails()!=null) {
				userDetails.setRecieveEmails(usersDetailsRequest.getRecieveEmails());
			}
		}
		
		userDetails.setModifiedBy("SYSTEM");
		userDetails.setModifiedOn(LocalDateTime.now());
		user.setUserDetails(userDetails);
		
	}
	
	
	public ResponseEntity<FollowResponse> followUser(int userId, int followId) throws BusinessExceptions {
		FollowResponse reponse = new FollowResponse();
		userService.addFollower(userId, followId);
		
		reponse.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<FollowResponse>(reponse, HttpStatus.CREATED);
	}
	

	public ResponseEntity<FollowResponse> unfollowUser(int userId, int followId) throws BusinessExceptions {
		FollowResponse reponse = new FollowResponse();
		userService.removeFollower(userId, followId);
		
		reponse.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<FollowResponse>(reponse, HttpStatus.OK);
	}
	
	public ResponseEntity<FollowResponse> getAllFollowDetailsForUser(int userId) throws BusinessExceptions {
		FollowResponse reponse = new FollowResponse();
		User user = userService.getAllFollowDetailsForUser(userId);
		
		reponse.setFollows(mapUserDataToDto(user.getFollows()));
		reponse.setFollowers(mapUserDataToDto(user.getFollowers()));
		reponse.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<FollowResponse>(reponse, HttpStatus.OK);
	}

	private UsersDto mapUserDataToDto(User user) {
//		UsersDto userDetailsDto=new UsersDto();
//		BeanUtils.copyProperties(user, userDetailsDto);
		return mapper.convertValue(user, UsersDto.class);
	}

	private List<UsersDto> mapUserDataToDto(Collection<User> users) {
		return users.parallelStream().map(user -> {
			return mapper.convertValue(user, UsersDto.class);
		}).collect(Collectors.toList());
//		List<UsersDto> userDetailsDtoList=new ArrayList<UsersDto>();
//		users.parallelStream().forEach(user -> {
//			UsersDto userDto = new UsersDto();
//			BeanUtils.copyProperties(user, userDto);
//			userDetailsDtoList.add(userDto);
//		});
//		return userDetailsDtoList;
	}

	private List<PostsDto> mapPostDataToPostsDto(List<Post> postData) {
//		List<PostsDto> postDtoList=new ArrayList<PostsDto>();
		return postData.parallelStream().map(post -> {
//			PostsDto postDto=new PostsDto();
//			BeanUtils.copyProperties(post, postDto);
			return mapper.convertValue(post, PostsDto.class);
//			postDtoList.add(postDto);
		}).collect(Collectors.toList());
		
//		return postDtoList;
	}

	private User mapUserRequestToUser(UsersRequest usersRequest) {
//		User user=new User();
//		BeanUtils.copyProperties(usersRequest, user);
		return mapper.convertValue(usersRequest, User.class);
	}


	private UserDetails mapUserDetailsRequest(UserDetailsRequest usersDetailsRequest) {
//		UserDetails userDetails=new UserDetails();
//		BeanUtils.copyProperties(usersDetailsRequest, userDetails);
		return mapper.convertValue(usersDetailsRequest, UserDetails.class);
	}

	private Post mapPostRequestToPost(PostRequest postRequest) {
		Post postData=new Post();
		BeanUtils.copyProperties(postRequest, postData);
		return postData;
//		return mapper.convertValue(postRequest, Post.class);
	}
	
	private PostsDto mapPostDataToPostsDto(Post postData) {
		PostsDto postDto=new PostsDto();
		BeanUtils.copyProperties(postData, postDto);
		return postDto;
	}
	
}
