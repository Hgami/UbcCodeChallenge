package com.ubc.qa.automation.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    tags = { "@ubc.github.gist.released"},
    monochrome = true, 
    glue = { "com.ubc.qa.automation.runner.stepdefs" },
    format = { "pretty", "html:target/cucumber", "json:target/cucumber.json" },
    features = { "src/test/resources/com/ubc/qa/automation/features" })
public class GitHubGistTestRunner {}

