package runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        monochrome = true,
        plugin = {"pretty", "html:target/Execution-Report.html", "json:target/Execution-Report.json"}
)
public class RunCukeTest {

    @BeforeClass
    public static void Setup() {
        System.out.println("In Test Runner-setup");
    }

    @AfterClass
    public static void teardown() {
        System.out.println("In Test Runner-teardown");
    }

}