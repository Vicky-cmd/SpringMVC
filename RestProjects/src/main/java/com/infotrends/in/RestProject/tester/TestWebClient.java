package com.infotrends.in.RestProject.tester;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TestWebClient {

	
	public static final String BASE_URI="https://api.twitter.com";
	public static final String ENDPOINT = "/2/tweets/search/recent";
	public static final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAPpWYgEAAAAA%2FJ77Hzke3Eb133sUrROXuBd5tow%3DYD0zGOez9exxxvH18fymPBMGuYlpIgyv8ZWRx5W7WDOMScZMEn";
	
	
	public void afterEach() {
		log.info("------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
	}
	
	public void testGet() {
		
		WebClient client = WebClient.create(BASE_URI);
		
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

//		log.info(response.getStatusCode());
	}
	

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
//			assertEquals(HttpStatus.OK, response.getStatusCode());
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
