package com.example.locationservice;

import com.example.locationservice.models.OverallMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationServiceApplicationTests {

	@Autowired
	private OverallMap overallMap;

	@Test
	void contextLoadsSmokeTest() {
		assertThat(overallMap).isNotNull();
	}

}
