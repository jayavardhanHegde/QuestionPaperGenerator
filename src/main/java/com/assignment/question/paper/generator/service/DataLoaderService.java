/*
 *
 */
package com.assignment.question.paper.generator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.question.paper.generator.config.ApplicationProperties;
import com.assignment.question.paper.generator.constants.ApplicationConstant;
import com.assignment.question.paper.generator.data.loader.DifficultQuestionLoader;
import com.assignment.question.paper.generator.data.loader.EasyQuestionLoader;
import com.assignment.question.paper.generator.data.loader.MediumQuestionLoader;
import com.assignment.question.paper.generator.data.loader.QuestionContext;
import com.assignment.question.paper.generator.model.QuestionLoader;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;

/**
 * @author jayavardhan.hegde
 *
 */

@Service
public class DataLoaderService {

	@Autowired
	private ApplicationProperties properties;

	public List<QuestionLoader> loadQuestions(String questionType) {
		QuestionContext questionContext;
		List<QuestionLoader> questionLoaderList = new ArrayList<>();
		QuestionLibrary.TOTAL_MARKS_AVAILABLE = ApplicationConstant.INTEGER_ZERO;
		switch (questionType) {
		case ApplicationConstant.TEXT_EASY:
		    questionContext = new QuestionContext(
			    new EasyQuestionLoader(properties.getMetaData(), properties.getEasyQuestionPath()));
			questionLoaderList.add(questionContext.loadQuestion());
			break;

		    case ApplicationConstant.TEXT_MEDIUM:
			questionContext = new QuestionContext(
				new MediumQuestionLoader(properties.getMetaData(), properties.getMediumQuestionPath()));
			questionLoaderList.add(questionContext.loadQuestion());
			break;

		    case ApplicationConstant.TEXT_DIFFICULT:
			questionContext = new QuestionContext(new DifficultQuestionLoader(properties.getMetaData(),
				properties.getDifficultQuestionPath()));
			questionLoaderList.add(questionContext.loadQuestion());
			break;

		    case ApplicationConstant.TEXT_ALL:
			questionContext = new QuestionContext(
				new EasyQuestionLoader(properties.getMetaData(), properties.getEasyQuestionPath()));
			questionLoaderList.add(questionContext.loadQuestion());

			questionContext = new QuestionContext(
				new MediumQuestionLoader(properties.getMetaData(), properties.getMediumQuestionPath()));
			questionLoaderList.add(questionContext.loadQuestion());

			questionContext = new QuestionContext(new DifficultQuestionLoader(properties.getMetaData(),
				properties.getDifficultQuestionPath()));
			questionLoaderList.add(questionContext.loadQuestion());

			break;
		default:
			break;
		}
		return questionLoaderList;
	}
}
