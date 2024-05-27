package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber", glue="Selenium.stepDefinitions",monochrome = true, tags= "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
// features= feature file path
//glue= step definition file path
//monochrome= To generate reports in readable format
//plugin = {"html:target/cucumber.html"} : Generate the reports in html format in folder target/cucumber.html(create)
// extends AbstractTestNGCucumberTests so that cucumber can scan and understand TestNG(annotations, aseertions, etc)