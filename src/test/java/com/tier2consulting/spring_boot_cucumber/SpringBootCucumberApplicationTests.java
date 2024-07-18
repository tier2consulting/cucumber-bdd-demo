package com.tier2consulting.spring_boot_cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = { "html:build/cucumber-report.html" },
	features = "src/test/resources/"
)
public class SpringBootCucumberApplicationTests {

	@Test
	void contextLoads() {
	}

}
