package com.infotrends.in.RestProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlacesModel {

	@JsonProperty("place name")
	private String name;
	
	private String longitude;
	
	private String state;
	
	@JsonProperty("state abbreviation")
	private String stateAbbr;
	
	private String latitude;
}
