package com.infotrends.in.RestProject.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZipCodeResponseModel {

	@JsonProperty("post code")
	private String postCode;
	
	private String country;
	
	@JsonProperty("country abbreviation")
	private String countryAbbr;
	
	@ToString.Exclude
	private List<PlacesModel> places = new ArrayList<>();
}
