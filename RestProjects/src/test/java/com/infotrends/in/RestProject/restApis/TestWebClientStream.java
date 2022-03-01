package com.infotrends.in.RestProject.restApis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.infotrends.in.RestProject.model.StreamRuleRequest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootTest
public class TestWebClientStream {

	
	public static final String BASE_URI="https://api.twitter.com";
	public static final String STREAM_ENDPOINT_PATH = "/2/tweets/search/stream";
	public static final String STREAM_ENDPOINT_RULES_PATH = "/2/tweets/search/stream/rules";
	public static final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAPpWYgEAAAAA%2FJ77Hzke3Eb133sUrROXuBd5tow%3DYD0zGOez9exxxvH18fymPBMGuYlpIgyv8ZWRx5W7WDOMScZMEn";
	
	
	@Autowired
	private WebClient.Builder builder;
	
	@AfterEach
	public void afterEach() {
		log.info("------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------"
				+ "------------------------------------------------------------------");
	}
	
	
	@Test
	public void testGetSubscribe() throws IOException {
		
		WebClient client = builder.baseUrl(BASE_URI)
					.defaultHeaders(header -> header.setBearerAuth(BEARER_TOKEN))
					.build();
		
		StreamRuleRequest ruleRequest = new StreamRuleRequest();
		
		ruleRequest.addRule("Latest News", "News");
		
		client.post()
			  .uri(STREAM_ENDPOINT_RULES_PATH)
			  .bodyValue(ruleRequest)
			  .retrieve()
			  .toBodilessEntity()
			  .subscribe(response -> {
				  
				  client.get()
				  .uri(STREAM_ENDPOINT_PATH)
				  .retrieve()
				  .bodyToFlux(String.class)
				  .filter(body -> !body.isBlank())
				  .subscribe(body -> {					  
					  
					  log.info(body);
					  
				  });
				  
			  });
		
		System.in.read();
	}
}
