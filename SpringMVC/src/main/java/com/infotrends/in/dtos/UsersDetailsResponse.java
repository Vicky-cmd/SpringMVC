package com.infotrends.in.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.infotrends.in.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "usersDetailsResponse")
@JsonTypeName(value = "usersDetailsResponse")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonInclude(value = Include.NON_NULL)
public class UsersDetailsResponse {

	private String status;
	
	private String userId;
	
}
