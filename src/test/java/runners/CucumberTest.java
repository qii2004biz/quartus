package runners;

import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith ( Cucumber.class )
@CucumberOptions(
        plugin = {
                "html:buld/cucumber-html-report"
                , "junit:build/cucumber-junit.xml"
                , "json:build/cucumber.json"
                , "pretty:build/cucumber-pretty.txt"
                , "usage:build/cucumber-usage.json"
        },
        features = {"src/test/resources/features"}
)
public class CucumberTest {
}
