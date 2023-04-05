package com.example.selenium;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/example/selenium/Resources/SVT-Play.feature",plugin = {"pretty", "html:target/cucumber_rapport"})
public class RunCucumberTest {

}
