package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/features/POST-api-test.feature",
                "src/test/features/GET-api-test.feature",
                "src/test/features/PUT-api-test.feature",
                "src/test/features/PATCH-api-test.feature",
                "src/test/features/Contract-validation.feature"},
        glue = {"org/example/restAssured/stepdefinitions"},
        tags = "",
        plugin = {
                "json:target/cucumber-report.json",
                "pretty", "html:target/cucumber-report/cucumber-report.html"
        }
)
public class RunnerTest {
}
