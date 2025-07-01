package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
plugin={"pretty","json:target/jsonReports/cucumber-report.json", "html:target/cucumber-report/cucumber.html"},
glue={"StepDefinition"})

public class TestRunner {

}
