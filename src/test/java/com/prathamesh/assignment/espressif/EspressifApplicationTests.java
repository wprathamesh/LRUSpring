package com.prathamesh.assignment.espressif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class EspressifApplicationTests {

	static RestTemplate template;
	private static Logger logger = LogManager.getLogger(EspressifApplicationTests.class);
	private String URL = "http://localhost:8081/";
	private String ADD_ITEM = "additem";
	private String GET_ITEM = "getitem";
	private List<String> listItems = Arrays.asList("Sachin", "Saurav", "Viru", "Yuvi");

	@BeforeAll
	static void initResources() {
		logger.info("Initializing Resttemplate");

		template = new RestTemplate();
		logger.info("Initialized Resttemplate");
	}

	@Test
	void addItems() {
		try {
			listItems.stream().forEach(player -> {
				template.getForObject(URL + ADD_ITEM + "?item=" + player, String.class);
			});
			logger.info("Items got inserted successsfully !!!");
		} catch (Exception e) {
			logger.error("Error while inserting items", e);
		}

	}

	@Test
	void checkItems() {
		List<String> listResp = listItems.stream()
				.map(player -> template.getForObject(URL + GET_ITEM + "?item=" + player, String.class))
				.collect(Collectors.toList());
		listResp.forEach(System.out::println);

		List<String> expected = Arrays.asList("Item not present in list", "Item not present in list", "Viru", "Yuvi");
		assertEquals(expected, listResp);
		

	}

}
