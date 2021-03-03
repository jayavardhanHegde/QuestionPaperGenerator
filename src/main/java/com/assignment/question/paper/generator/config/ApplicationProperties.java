/*
 *
 */
package com.assignment.question.paper.generator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author jayavardhan.hegde
 *
 */
@Component
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

	@Value("${question.metadata}")
	private String metaData;

	@Value("${easy.questions.file.path}")
	private String easyQuestionPath;

	@Value("${medium.question.file.path}")
	private String mediumQuestionPath;

	@Value("${difficult.question.file.path}")
	private String difficultQuestionPath;

	public String getMetaData() {
		return this.metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public String getEasyQuestionPath() {
	    return easyQuestionPath;
	}

	public void setEasyQuestionPath(String easyQuestionPath) {
	    this.easyQuestionPath = easyQuestionPath;
	}

	public String getMediumQuestionPath() {
	    return mediumQuestionPath;
	}

	public void setMediumQuestionPath(String mediumQuestionPath) {
	    this.mediumQuestionPath = mediumQuestionPath;
	}

	public String getDifficultQuestionPath() {
	    return difficultQuestionPath;
	}

	public void setDifficultQuestionPath(String difficultQuestionPath) {
	    this.difficultQuestionPath = difficultQuestionPath;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
