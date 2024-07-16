package com.tier2consulting.spring_boot_cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
class SpringBootCucumberApplicationTests {

	@Test
	void contextLoads() {
	}

}
