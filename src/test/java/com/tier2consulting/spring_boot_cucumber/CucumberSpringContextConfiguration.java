package com.tier2consulting.spring_boot_cucumber;


import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootCucumberApplication.class)
@CucumberContextConfiguration
public class CucumberSpringContextConfiguration {
}
