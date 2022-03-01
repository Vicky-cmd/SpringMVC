package com.infotrends.in.RestProject.restApis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotrends.in.RestProject.model.GoRestRequest;
import com.infotrends.in.RestProject.model.PlacesModel;
import com.infotrends.in.RestProject.model.ZipCodeResponseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
public class TestHttpClient {
	
	private static class GoRest {
		private static final String URI_ENDPOINT = "https://gorest.co.in/public/v1/users";
		private static final String AUTHORIZATION="Authorization";
		private static final String ACCESS_TOKEN = "Bearer 8f79562997dba68d1bf18c3776bb0600639c7d2e56fc128374a247623b967638";
		private static final String CONTENT_TYPE="Content-Type";
		private static final String CONTENT_TYPE_VALUE="application/json";
		
	}

	private static final String URI_ENDPOINT = "http://api.zippopotam.us/";
	public static final String COUNTRY_CODE="IN";
	
	private ObjectMapper mapper;
	
	@BeforeAll
	public void setup() {
		mapper= new ObjectMapper();
		mapper.configure(Feature.IGNORE_UNDEFINED, true);
		
	}
	
	
	@Test
	public void testGet() {
		

		try {
			String pinCode="600003";
			
			
			HttpClient client = HttpClient.newBuilder()
										  .connectTimeout(Duration.of(5, ChronoUnit.SECONDS))
										  .version(Version.HTTP_2)
										  .build();
			
			log.info(String.format("URI - %s", URI.create(URI_ENDPOINT + COUNTRY_CODE + "/" + pinCode)));
			HttpRequest request = HttpRequest.newBuilder()
									.uri(URI.create(URI_ENDPOINT + COUNTRY_CODE + "/" + pinCode))
									.GET()
									.build();
			
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				
				assertEquals(HttpStatus.OK.value(), response.statusCode());
				log.info(response.body());
				assertThat(response.body()).isNotEmpty().contains("Madras Medical College");
				
				ZipCodeResponseModel zipResponse = mapper.readValue(response.body(), ZipCodeResponseModel.class);
				
				log.info(zipResponse.toString());
				assertNotNull(zipResponse);
				assertThat(zipResponse.getCountry()).isNotEmpty().isEqualToIgnoringCase("INDIA");
				assertThat(zipResponse.getPlaces()).isNotEmpty().hasSize(3).map(PlacesModel::getName).contains("Madras Medical College").contains("Central Station");
				
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@Test
	public void testGetAsync() {
		

		String pinCode="600003";
		
		
		HttpClient client = HttpClient.newBuilder()
									  .connectTimeout(Duration.of(5, ChronoUnit.SECONDS))
									  .version(Version.HTTP_2)
									  .build();
		
		log.info(String.format("URI - %s", URI.create(URI_ENDPOINT + COUNTRY_CODE + "/" + pinCode)));
		HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(URI_ENDPOINT + COUNTRY_CODE + "/" + pinCode))
								.GET()
								.build();
		
		client.sendAsync(request, BodyHandlers.ofString())
			  .thenApply(response -> {
				  response.statusCode();
				  return response.body();
			  })
			  .thenAccept(response -> {
				  	log.info(response);
				  	assertThat(response).isNotEmpty().contains("Madras Medical College");
				  
					ZipCodeResponseModel zipResponse;
					try {
						zipResponse = mapper.readValue(response, ZipCodeResponseModel.class);
					
						log.info(zipResponse.toString());
						assertNotNull(zipResponse);
						assertThat(zipResponse.getCountry()).isNotEmpty().isEqualToIgnoringCase("INDIA");
						assertThat(zipResponse.getPlaces()).isNotEmpty().hasSize(3).map(PlacesModel::getName).contains("Madras Medical College").contains("Central Station");
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  });
		
		
		
	}


	@Test
	public void testPut() {

		String id="3099";

		try {
			HttpClient client = HttpClient.newBuilder()
					  .connectTimeout(Duration.of(5, ChronoUnit.SECONDS))
					  .version(Version.HTTP_2)
					  .build();
	
			log.info(String.format("URI - %s", URI.create(GoRest.URI_ENDPOINT + "/" + id)));
			GoRestRequest requestObj = new GoRestRequest("Tenali Ramakrishna", "tenali.2.ramakrishna@15ce.com", "male", "active");
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(GoRest.URI_ENDPOINT + "/" + id))
					.header(GoRest.CONTENT_TYPE, GoRest.CONTENT_TYPE_VALUE)
					.header(GoRest.AUTHORIZATION, GoRest.ACCESS_TOKEN)
					.PUT(BodyPublishers.ofString(mapper.writeValueAsString(requestObj)))
					.build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			log.info(response.body());
			assertEquals(HttpStatus.OK.value(), response.statusCode());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
