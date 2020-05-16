package com.game.mancala.kalah;

import com.game.mancala.kalah.presentation.data.NewGameResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KalahApplicationTests {

	private static final String HOST = "http://localhost";

	@Resource
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	protected int port;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createGame() {
		HashMap<String, String> headers = new HashMap<>();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
		headers.forEach(httpHeaders::add);

		ResponseEntity<String> result = testRestTemplate.exchange(
				HOST + ":" + port + "/games",
				HttpMethod.POST,
				new HttpEntity<>(httpHeaders),
				String.class);

		assertEquals(CREATED, result.getStatusCode());
	}

	@Test
	public void playGame() {
		HashMap<String, String> headers = new HashMap<>();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
		headers.forEach(httpHeaders::add);

		ResponseEntity<NewGameResponse> result = testRestTemplate.exchange(
				HOST + ":" + port + "/games",
				HttpMethod.POST,
				new HttpEntity<>(httpHeaders),
				NewGameResponse.class);

		ResponseEntity<String> playResult = testRestTemplate.exchange(
				HOST + ":" + port + "/games/"+result.getBody().getId()+"/pits/"+1,
				HttpMethod.PUT,
				new HttpEntity<>(httpHeaders),
				String.class);

		assertEquals(OK, playResult.getStatusCode());
	}


}
