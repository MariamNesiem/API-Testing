package API.AAM.Platform.Test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"steps"},
        		plugin = {
                        "pretty",
                        "html:target/reporting/cucumber.html"
                })
public class TestRunner {

}
