package com.spring.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAssignmentApplicationTests {

	@Test
	void testApplication() {

		Assertions.assertEquals(1, 2-1);
	}

}
