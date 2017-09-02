package com.hooks;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by a.arulelango on 04/08/2017.
 */

    @RunWith(Cucumber.class)
    @CucumberOptions(plugin = {"pretty", "cucumber.runtime.formatter.CucumberJSONFormatter:target/cucumber.json", "json:target//cucumber/TestResult.json","html:target//cucumber-html-reports/"},
            features = {"src/test/resources/features"},
            glue = {"com.vehicle.steps"},
           tags = {"@Functional"}, monochrome = true)
    public class CucumberHooks {

}
