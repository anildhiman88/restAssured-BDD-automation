package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/features/POST-api-test.feature",
                    "src/test/features/GET-api-test.feature",
                    "src/test/features/PUT-api-test.feature",
                    "src/test/features/PATCH-api-test.feature"},
        glue = {"org/example/restAssured/stepdefinitions"},
        tags = "@firstTest",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/CucumberTestReport.xml"
        }
)
public class RunnerTest {
}
