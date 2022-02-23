package com.infotrends.in.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.infotrends.in.utils.EntityType;
import com.infotrends.in.utils.UserRole;
import com.infotrends.in.utils.UserStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id", unique = true, updatable = false)
	private int userId;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "fullname")
	private String FullName;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private UserStatus status;

	@Column(name = "userRole")
	private UserRole userRole;
	
	@Column(name = "accountType")
	private EntityType accountType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;

	@JsonIgnore(value = true)
	@OneToMany(targetEntity = Post.class , fetch = FetchType.LAZY, mappedBy = "author", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Post> writtenPosts;
	
	
	@JsonIgnore(value = true)
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "follow_mappings",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="follower_id")
			)
	private Set<User> followers;
	
	@JsonIgnore(value = true)
	@ToString.Exclude
	@ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
	private Set<User> follows;
	

	@JsonIgnore(value = true)
	@OneToMany(mappedBy = "requestor")
	private List<FollowRequests> requestsByMe;

	@JsonIgnore(value = true)
	@OneToMany(mappedBy = "user")
	private List<FollowRequests> requestsToMe;
	
	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_on")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime modifiedOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_on")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime createdOn;
	

	public void addFollowRequest(FollowRequests request) {
		if(requestsToMe==null) requestsToMe=new ArrayList<FollowRequests>();
		request.setUser(this);
		requestsToMe.add(request);
	}
	
	public void addPost(Post post) {
		if(writtenPosts==null) writtenPosts=new ArrayList<Post>();
		
		post.setAuthor(this);
		writtenPosts.add(post);
	}
	
	public void addFollower(User user) {
		if(followers==null) followers=new HashSet<User>();
		
		followers.add(user);
	}
	
}
