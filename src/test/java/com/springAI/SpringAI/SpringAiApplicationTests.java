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
	private com.springAI.SpringAI.Controllers.ChatController chatController;

	@Test
	void contextLoads() {
	}

	@Test
	void testOpenAiMissingKey() {
		String response = chatController.openai("hello");
		System.out.println("OpenAI Response: " + response);
		org.junit.jupiter.api.Assertions.assertEquals("OpenAI API key not found", response);
	}

	@Test
	void testGeminiLive() {
		String response = chatController.gemini("Say hello in one short word");
		System.out.println("Gemini Response: " + response);
		assertNotNull(response);
		assertFalse(response.isBlank());
	}
}
