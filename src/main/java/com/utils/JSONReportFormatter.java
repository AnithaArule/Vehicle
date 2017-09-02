package com.utils;

import cucumber.runtime.formatter.CucumberJSONFormatter;
import cucumber.runtime.io.URLOutputStream;
import cucumber.runtime.io.UTF8OutputStreamWriter;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.TagStatement;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONReportFormatter extends CucumberJSONFormatter {
	


	private static final Pattern VERSION_PATTERN = Pattern.compile("v\\d{1,2}");
	private final String prefix;

	public JSONReportFormatter(URL url) {
		super(getAppendable(url));
		Matcher matcher = VERSION_PATTERN.matcher(url.getPath());
		if (matcher.find()) {
			this.prefix = matcher.group();
		} else {
			this.prefix = "";
		}
	}

	private static Appendable getAppendable(URL url) {
		try {
			return new UTF8OutputStreamWriter(new URLOutputStream(url));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void uri(String uri) {
		super.uri("uri/" + prefix + "/" + uri);
	}

	@Override
	public void feature(Feature feature) {
		addNameAndIdVersionSuffix(feature);
		super.feature(feature);
	}

	@Override
	public void scenarioOutline(ScenarioOutline scenarioOutline) {
		addNameAndIdVersionSuffix(scenarioOutline);
		super.scenarioOutline(scenarioOutline);
	}

	@Override
	public void scenario(gherkin.formatter.model.Scenario scenario) {
		addNameAndIdVersionSuffix(scenario);
		super.scenario(scenario);
	}

	private void addNameAndIdVersionSuffix(TagStatement tagStatement) {
		if (StringUtils.isNotEmpty(prefix)) {
			addSuffix(tagStatement, "id", tagStatement.getId(), ";" + prefix);
			addSuffix(tagStatement, "name", tagStatement.getName(), " " + prefix);
		}
	}

	private static void addSuffix(Object object, String field, String value, String suffix) {
		try {
			FieldUtils.writeField(object, field, value + suffix, true);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}


}
