import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        tags = "@all",
        plugin = {
                "pretty"
                , "json:target/cucumber.json"
                , "html:target/reports/cucumber-pretty.html"
                , "timeline:target/reports/timeline"
        }
)
public class TestRunner {
}