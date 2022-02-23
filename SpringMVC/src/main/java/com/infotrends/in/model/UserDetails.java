package com.infotrends.in.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.infotrends.in.utils.UserRole;
import com.infotrends.in.utils.UserStatus;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "user_details")
@Data
public class UserDetails {
	
	@Id
	@GeneratedValue
	private int Id;
	
	@Column(name = "user_bio", length = 300)
	private String userBio;
	
	@Column(name = "profile_links")
	private String profileLinks;
	
	@Column(name = "profile_uri")
	private String profileURI;

	@Column(name = "dob")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfBirth;
	
	@Column(name = "hobby", length = 200)
	private String hobby;
	
	@Column(name = "recieve_emails")
	private Boolean recieveEmails;
	
	@Column(name = "occupation", length = 100)
	private String occupation;
	
	@JsonIgnore(value = true)
	@OneToOne(mappedBy = "userDetails", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@ToString.Exclude
	private User user;
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
}
