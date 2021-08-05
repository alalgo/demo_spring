package org.alalgo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommonControllerTest{
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	
    @Autowired  
    private CommonController controller;  
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/post",
				String.class)).contains("Hello, World");
	}	
}
