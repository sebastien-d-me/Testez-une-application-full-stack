package com.openclassrooms.starterjwt;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {

	@Test
    /// Test - Chargement
	public void contextLoads() {
	}


    @Test
    /// Test - Main
    public void testMain() {
        SpringBootSecurityJwtApplication.main(new String[]{});
    }
}
