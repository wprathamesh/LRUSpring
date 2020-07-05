package com.prathamesh.assignment.espressif;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.prathamesh.assignment.espressif.dao.LRUCacheDAO;
import com.prathamesh.assignment.espressif.dao.impl.InMemoryCacheDAOImpl;

@SpringBootApplication
public class EspressifApplication {

	private static final Logger logger = LogManager.getLogger(EspressifApplication.class);

	@Bean
	public LRUCacheDAO getLRUCacheDao() {
		return new InMemoryCacheDAOImpl();
	}

	public static void main(String[] args) {
		try {
			logger.info("Starting application");
			SpringApplication.run(EspressifApplication.class, args);
			logger.info("Closing application");
		} catch (Exception e) {
			logger.error("Exception occured %s ", e);
		}
	}

}
