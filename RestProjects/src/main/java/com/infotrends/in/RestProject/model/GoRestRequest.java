package com.infotrends.in.RestProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
@AllArgsConstructor
public class GoRestRequest {

	private String name;
	private String email;
	private String gender;
	private String status;
}
