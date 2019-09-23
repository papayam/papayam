package com.papayam.demo;

import com.github.papayam.core.CucumberRunner;
import cucumber.api.CucumberOptions;

@CucumberOptions(strict = true, monochrome = true,
        features = "src/main/resources/features/toutiao.feature",
        glue = "com.github.papayam.step",
        plugin = {"pretty", "html:target/cucumber-html-report;","json:target/cucumber.json" })
public class DemoRunner extends CucumberRunner { }
