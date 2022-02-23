package com.infotrends.in.dtos;

import java.util.List;
import java.util.Set;

import com.infotrends.in.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowResponse {

	private int status;
	private User user;
	private List<UsersDto> follows;
	private List<UsersDto> followers;
}
