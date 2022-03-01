package com.infotrends.in.RestProject.restApis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infotrends.in.RestProject.client.ZipCodeClient;
import com.infotrends.in.RestProject.model.PlacesModel;
import com.infotrends.in.RestProject.model.ZipCodeResponseModel;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TestOpenFeign {

	@Autowired
	private ZipCodeClient zipCodeClient;
	
	@Test
	public void testGet() {
		ZipCodeResponseModel zipResponse = zipCodeClient.getData(TestHttpClient.COUNTRY_CODE, "600003");
		
		log.info(zipResponse.toString());
		assertNotNull(zipResponse);
		assertThat(zipResponse.getCountry()).isNotEmpty().isEqualToIgnoringCase("INDIA");
		assertThat(zipResponse.getPlaces()).isNotEmpty().hasSize(3).map(PlacesModel::getName).contains("Madras Medical College").contains("Central Station");
	}
}
