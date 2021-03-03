package com.assignment.question.paper.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.assignment.question.paper.generator.controller",
	"com.assignment.question.paper.generator.service",
	"com.assignment.question.paper.generator.data.loader",
        "com.assignment.question.paper.generator.model", "com.assignment.question.paper.generator.util",
        "com.assignment.question.paper.generator.validator", "com.assignment.question.paper.generator.config" })
public class QuestionPaperGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionPaperGeneratorApplication.class, args);


	}
}
