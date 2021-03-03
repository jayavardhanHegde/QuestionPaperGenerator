/*
 *
 */
package com.assignment.question.paper.generator.service;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.assignment.question.paper.generator.controller.QuestionGeneratorController;
import javafx.application.Application;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.question.paper.generator.config.AppConfig;
import com.assignment.question.paper.generator.constants.ApplicationConstant;
import com.assignment.question.paper.generator.constants.ErrorConstant;
import com.assignment.question.paper.generator.exception.QuestionLibraryException;
import com.assignment.question.paper.generator.exception.QuestionPaperValidationException;
import com.assignment.question.paper.generator.model.Question;
import com.assignment.question.paper.generator.model.QuestionPaper;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;
import com.assignment.question.paper.generator.util.QuestionPaperUtil;
import com.assignment.question.paper.generator.validator.ValidatorUtil;

/**
 * @author jayavardhan.hegde
 *
 */

@Service
public class QuestionPaperGeneratorService {

	Logger logger = LoggerFactory.getLogger(QuestionPaperGeneratorService.class);

	@Autowired
	private QuestionPaperUtil questionPaperUtil;

	@Autowired
	private QuestionPaper questionPaper;

	@Autowired
	private AppConfig config;

	@Autowired
	private ValidatorUtil validatorUtil;

	public QuestionPaper generateQuestionPapaer(Integer totalMarks, String stratergy, Integer[] distributions,String[] topicDistributions) {
		// Validate if the distributions are proper
		validatorUtil.isDistributionProper(distributions);
		validatorUtil.isEnoughMarksAvailableToGeneratePaper(totalMarks);
		Set<Question> questionPaperSet = pickQuestionsFromStore(totalMarks, distributions,stratergy,topicDistributions);
		validateGeneratedQuestions(questionPaperSet, totalMarks, distributions);
		prepareQuestionPaperModel(totalMarks, questionPaperSet);
		return questionPaper;
	}

	private Set<Question> pickQuestionsFromStore(Integer totalMarks, Integer[] distributions,String stratergy, String[] topicDistributions) {
		Set<Question> questionPaperSet = new HashSet<Question>();
		Map<String, Double> distributionMap = null;
		if (distributions.length != 0) {
			if (stratergy.equals(ApplicationConstant.STRATERGY_DIFFICULTY)) {
				distributionMap = questionPaperUtil.calculateDistributionOfQuestions(totalMarks,
						distributions);
				logger.debug("Distribution for Each Marks is " , distributionMap.toString());
				questionPaperSet.addAll(extractQuestionsForMarksDistribution(distributionMap.get(ApplicationConstant.TEXT_EASY),
						QuestionLibrary.multiKeyEasyQuestionsMap, ApplicationConstant.TEXT_EASY));
				questionPaperSet.addAll(extractQuestionsForMarksDistribution(distributionMap.get(ApplicationConstant.TEXT_MEDIUM),
						QuestionLibrary.multiKeyMediumQuestionsMap, ApplicationConstant.TEXT_MEDIUM));
				questionPaperSet.addAll(extractQuestionsForMarksDistribution(distributionMap.get(ApplicationConstant.TEXT_DIFFICULT),
						QuestionLibrary.multiKeyDifficultQuestionsMap, ApplicationConstant.TEXT_DIFFICULT));
			} else if(stratergy.equals(ApplicationConstant.STRATERGY_TOPIC)){
				//To-DO : Just reference implementation for Topic based question distributions.
				distributionMap = questionPaperUtil.calculateDistributionOfQuestions(totalMarks, topicDistributions);
				questionPaperSet.addAll(extractQuestionsForTopicDistribution(distributionMap.get(""),QuestionLibrary.multiKeyEasyQuestionsMap,topicDistributions));
			}
		}
		return questionPaperSet;
	}

	private void prepareQuestionPaperModel(Integer totalMarks, Set<Question> questionPaperSet) {
		questionPaper.setTotalMarks(totalMarks);
		questionPaper.setTotalQuestions(questionPaperSet.size());
		questionPaper.setDuration(ApplicationConstant.DURATION);
		questionPaper.setInstructions(ApplicationConstant.INSTRUCTIONS);
		questionPaper.setQuestions(questionPaperSet);
	}

	private void validateGeneratedQuestions(Set<Question> questionPaperSet, Integer totalMarks,
		Integer[] distributions) {
	    Integer generatedMarks = questionPaperSet.stream().map(Question::getMarks).reduce(0, (a, b) -> a + b);
	    if (totalMarks != generatedMarks) {
		throw new QuestionPaperValidationException(
			ErrorConstant.DISTRIBUTION_NOT_SATISFIED,
			MessageFormat.format(
				config.messageSource().getMessage(ErrorConstant.DISTRIBUTION_NOT_SATISFIED,
					null,
					Locale.ENGLISH),
				distributions[0] + "," + distributions[1] + "," + distributions[2]));
	    }
	}

	private Set<Question> extractQuestionsForMarksDistribution(Double marks, MultiKeyMap questionMap, String type) {
		Set<Question> questionPaperSet = new HashSet<Question>();
		try{
			Double noOfQuestions = getNumberOfQuestions(marks, type);
			Integer generatedMarks = 1;
			while (generatedMarks <= noOfQuestions) {
				Question question = (Question) questionMap.get(generatedMarks, type);
				if (question != null) {
					questionPaperSet.add(question);
				}
				generatedMarks++;
			}
			return questionPaperSet;
		}catch (Exception e){
			throw new QuestionLibraryException(ErrorConstant.QUESTION_LIBRARY_EMPTY,
					config.messageSource().getMessage(ErrorConstant.QUESTION_LIBRARY_EMPTY, null, Locale.ENGLISH));
		}
	}

	private Set<Question> extractQuestionsForTopicDistribution(Double marks, MultiKeyMap questionMap, String[] topics){
		Set<Question> questionPaperSet = new HashSet<Question>();
		//To-Do : Implement logic to pick questions from the Store based on topic
		return questionPaperSet;
	}

	private Double getNumberOfQuestions(Double marks, String type) {
	    // Checking for only two types
	    return type.equalsIgnoreCase("DIFFICULT") ? marks / 10 : marks / 5;
	}

}