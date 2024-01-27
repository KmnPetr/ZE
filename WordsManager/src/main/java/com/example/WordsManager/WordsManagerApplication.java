package com.example.WordsManager;

import com.example.WordsManager.services.VoiceFileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WordsManagerApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WordsManagerApplication.class, args);

		context.getBean(VoiceFileService.class).printListFiles();

	}
}