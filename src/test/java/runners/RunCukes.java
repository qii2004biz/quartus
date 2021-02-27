package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith ( Cucumber.class )
@CucumberOptions(
        plugin = {
                "html:build/cucumber-html-report"
                , "junit:build/cucumber-junit.xml"
                , "json:build/cucumber.json"
                , "pretty:build/cucumber-pretty.txt"
                , "usage:build/cucumber-usage.json"
        },
        features = {"src/test/resources/features"}
        ,glue={"cucumber/stepDefinitions"}
)
public class RunCukes {
}
