package com.infotrends.in.dtos;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
import com.infotrends.in.validations.ValidateEnums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName(value = "postRequest")
@JsonTypeName(value = "postRequest")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonTypeInfo(use = Id.NAME, include = As.WRAPPER_OBJECT)
@JsonInclude(value = Include.NON_NULL)
public class PostRequest {
	
	@NotNull(message = "Title is mandatory")
	@Size(min = 1, message = "Title is mandatory")
	private String title;
	
	@NotNull(message = "Please provide a vailid content")
//	@Max(value = 500, message = "The post can only be 500 characters long")
	@javax.validation.constraints.Size.List({
		@Size(min = 10, message = "The post must be atleast 10 characters Long"),
		@Size(max = 500, message = "The post can only be 500 characters long")
	})
	private String content;

	
	private String attachments;
	
//	@NotNull(message = "Please provide the user id of the author")
//	@Pattern(regexp = "^[0-9]*$", message = "Only numbers are allowed")
	private int author;
	
	@ValidateEnums(value = "PUBLIC,PRIVATE", message = "Please Povde a valid Role", mandatory = true)
	private String postType;
}
