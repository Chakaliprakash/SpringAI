package com.springAI.SpringAI;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.springAI.SpringAI.Service.Template;

@SpringBootTest
class SpringAiApplicationTests {

	@Autowired
	private Template tenTemplate; 

	@Test
	void contextLoads() {
	}

	@Test
	void checkTemplate() {
		String response = tenTemplate.promptTemplate();
		System.out.println(response);

		assertNotNull(response);
        assertFalse(response.isBlank());

	}
}
