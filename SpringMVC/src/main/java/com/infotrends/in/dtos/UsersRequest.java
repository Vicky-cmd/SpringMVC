package com.infotrends.in.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
import com.infotrends.in.validations.ValidateEnums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "usersRequest")
@JsonTypeName(value = "usersRequest")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonInclude(value = Include.NON_NULL)
public class UsersRequest {
	
	@NotNull(message = "Username is Mandatry")
	@Size(min = 1, message = "Username is Mandatry")
	private String username;
	
	@NotNull(message = "FullName is Mandatry")
	@Size(min = 2, message = "FullName is Mandatry")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "FullName can only contain Characters")
	private String FullName;
	
	@NotNull(message = "Password is Mandatory")
	@Size(min = 3, message = "Password Must be atleast 3 Characters Long")
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String password;

	@ValidateEnums(value ="ACTIVE,LOCKED,DELETED,EXPIRED", message = "Please Povde a valid status")
	private String status;

	@ValidateEnums(value = "USER,MODERATOR,ADMIN", message = "Please Povde a valid Role")
	private String userRole;

	@ValidateEnums(value = "PUBLIC,PRIVATE", message = "Please Povde a valid Role", mandatory = true)
	private String accountType;
}
