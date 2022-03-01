package com.infotrends.in.RestProject.restApis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class TestWebClient {

	
	public static final String BASE_URI="https://api.twitter.com";
	public static final String ENDPOINT = "/2/tweets/search/recent";
	public static final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAPpWYgEAAAAA%2FJ77Hzke3Eb133sUrROXuBd5tow%3DYD0zGOez9exxxvH18fymPBMGuYlpIgyv8ZWRx5W7WDOMScZMEn";
	
	
	@AfterEach
	public void afterEach() {
		log.info("------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
	}
	
	@Test
	public void testGet() {
		
		WebClient client = WebClient.create(BASE_URI);

//		WebClient client = WebClient.builder().build();

		Mono<ResponseEntity<String>> mono = client.get()
					.uri(ENDPOINT + "?query={query}", "Avengers")
					.header("Authorization", "Bearer " + BEARER_TOKEN)
					.retrieve()
					.toEntity(String.class);
		
		ResponseEntity<String> response = mono.block();

		log.info("------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
		log.info(response.getBody());

		log.info("------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	

	@Test
	public void testGetAsync() {
		
		WebClient client = WebClient.create(BASE_URI);
		
		Mono<ResponseEntity<String>> mono = client.get()
					.uri(ENDPOINT + "?query={query}", "Avengers")
					.header("Authorization", "Bearer " + BEARER_TOKEN)
					.retrieve()
					.toEntity(String.class);
		
		mono.subscribe(response -> {

			log.info("------------------------------------------------------------------"
					+ "------------------------------------------------------------------"
					+ "------------------------------------------------------------------"
					+ "------------------------------------------------------------------");
			log.info(response.getBody());

			log.info("------------------------------------------------------------------"
					+ "------------------------------------------------------------------"
					+ "------------------------------------------------------------------"
					+ "------------------------------------------------------------------");
			assertEquals(HttpStatus.OK, response.getStatusCode());
		});

		log.info("------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
		log.info("Response will be received asynchronously!");

		log.info("------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
	}
}
