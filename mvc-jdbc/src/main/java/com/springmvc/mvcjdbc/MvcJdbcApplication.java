package com.springmvc.mvcjdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MvcJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcJdbcApplication.class, args);
		log.info("Number of Processors: {}", Runtime.getRuntime().availableProcessors());
	}

}
