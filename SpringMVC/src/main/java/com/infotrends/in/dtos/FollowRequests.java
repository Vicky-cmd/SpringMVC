package com.infotrends.in.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.infotrends.in.validations.ValidateEnums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "followRequests")
@JsonTypeName(value = "followRequests")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonInclude(value = Include.NON_NULL)
public class FollowRequests {
	
	@NotNull(message = "Request Id is mandatory")
	@Pattern(regexp = "^[0-9]*$", message = "Only numbers are allowed")
	private String requestId;
	
	@ValidateEnums(value = "A,D,B", message = "Please Povde a valid Role", mandatory = true)
	private String status;

}
