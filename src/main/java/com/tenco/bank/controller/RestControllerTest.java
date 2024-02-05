package com.tenco.bank.controller;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenco.bank.dto.Todo;
import com.tenco.bank.dto.Todo2;

@RestController // DATA 를 내려줌
public class RestControllerTest {

	// 클라이언트에서 접근하는 주소 설계
	@GetMapping("/my-test1")
	public ResponseEntity<String> myTest1() {
		// 여기서 다른 서버로 자원을 요청한 다음
//		다시 클라이언트에게 자원을 내려주자

		// 먼저 URI 객체 만들기
		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com").path("/todos").encode()
				.build().toUri();

		RestTemplate restTemplate = new RestTemplate();
		// HTTP 통신 --> HTTP 메세지 헤더, 바디를 구성해서 보내야한다.

		// 헤더 구성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json; charset=UTF-8");

//		바디 구성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("title", "블로그 포스트 1");
		params.add("body", "후미진 어느 언덕에서 도시락 소풍");
		params.add("userId", "1");

//		헤더와 바디 결합
		HttpEntity<MultiValueMap<String, String>> requestMessage = new HttpEntity<>(params, headers);

//		HTTP 요청처리 , 자동으로 class 파싱이 됩니다.
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, requestMessage, String.class);

		System.out.println("headers : " + response.getHeaders());

		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<?> test2(@PathVariable Integer id) {
		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com").path("/todos").path("/" + id).encode()
				.build().toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Todo> response = restTemplate.getForEntity(uri, Todo.class); // GET 방식 요청 응답은??
		
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	
	@GetMapping("/todos/update/{id}")
	public ResponseEntity<?> test3(@PathVariable Integer id) {
		
		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com")
				.path("/todos").path("/" + id).encode()
				.build().toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json; charset=UTF-8");
		
//		바디 구성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("id", String.valueOf(id));
		params.add("title", "foo");
		params.add("body", "바디 부분");
		params.add("userId", "1");

		HttpEntity<MultiValueMap<String ,String>> requestMessage = new HttpEntity<>(params , headers);
		
		ResponseEntity<Todo2> response = restTemplate.exchange(uri, HttpMethod.PUT , requestMessage , Todo2.class);

		
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
}
