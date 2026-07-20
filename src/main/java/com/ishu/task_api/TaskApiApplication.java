package com.ishu.task_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TaskApiApplication {
	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskApiApplication.class, args);
	}

}
