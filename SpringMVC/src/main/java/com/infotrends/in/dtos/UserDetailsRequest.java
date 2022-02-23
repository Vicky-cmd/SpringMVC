package com.infotrends.in.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.infotrends.in.utils.UserRole;
import com.infotrends.in.utils.UserStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "userDetailsRequest")
@JsonTypeName(value = "userDetailsRequest")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonInclude(value = Include.NON_NULL)
public class UserDetailsRequest {
	
//	@NotNull(message = "User Id is mandatory")
//	@Pattern(regexp = "^[0-9]*$", message = "Only numbers are allowed")
	private int userId;
	
	private String userBio;
	
	private String profileLinks;
	
	private String profileURI;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	private String hobby;
	
	private Boolean recieveEmails;
	
	private String occupation;
	
}
