package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="features",
		glue={"stepDefinitions"},
		plugin={"html:target/cucumber-html-report","pretty:target/cucumber-pretty.txt"})
public class TestRunnerMain {

}
