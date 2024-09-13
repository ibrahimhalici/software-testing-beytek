package com.beytek.software_testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SoftwareTestingApplicationTests {

	@Autowired
	private Controller controller;

	// smoke test
	@Test
	void contextLoads() {
	assertThat(controller).isNotNull();
	}

}
