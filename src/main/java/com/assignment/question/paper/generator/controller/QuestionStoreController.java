/*
 *
 */
package com.assignment.question.paper.generator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.question.paper.generator.model.QuestionLoader;
import com.assignment.question.paper.generator.service.DataLoaderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * @author jayavardhan.hegde
 *
 */

@RestController
@RequestMapping("/store")
@Api(value = "Question Load REST End Point", description = "On successful completion questions will be loaded to Data store")
public class QuestionStoreController {

	@Autowired
	private DataLoaderService dataLoaderService;

	@PostMapping("/load/{questionType}")
	public ResponseEntity<Object> loadQuestions(
		@PathVariable("questionType")
		@ApiParam(value = "EASY = To Load only EASY Questions to Store \n" +
				"MEDIUM = To Load only MEDIUM Questions to Store \n" +
				"DIFFICULT = To Load only MEDIUM Questions to Store \n" +
				"ALL = To load EASY,MEDIUM,DIFFICULT Questions to Store") String questionType) {
		List<QuestionLoader> questionLoader = dataLoaderService.loadQuestions(questionType.toUpperCase());
		if(questionLoader.isEmpty()){
			return new ResponseEntity<Object>(questionLoader, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
		return new ResponseEntity<Object>(questionLoader, HttpStatus.CREATED);
	}
}
