/*
 *
 */
package com.assignment.question.paper.generator.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.question.paper.generator.exception.QuestionLibraryException;
import com.assignment.question.paper.generator.exception.QuestionPaperValidationException;
import com.assignment.question.paper.generator.model.ApiError;
import com.assignment.question.paper.generator.model.QuestionPaper;
import com.assignment.question.paper.generator.service.QuestionPaperGeneratorService;

/**
 * @author jayavardhan.hegde
 *
 */

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
@Api(value = "Question Generator REST End Point", description = "Generates Question Paper based on the Parameters given")
public class QuestionGeneratorController {

    @Autowired
    private QuestionPaperGeneratorService questionPaperGeneratorService;

    Logger logger = LoggerFactory.getLogger(QuestionGeneratorController.class);

    @GetMapping("/generate")
    public ResponseEntity<Object> generatePaper(
            @Valid @RequestParam(required = true, value = "totalMarks")
                @ApiParam(value = "Total Marks Question paper should consist of. Minimum marks is 5 & Maxium marks is 200")
                @Min(value = 5, message = "Total marks should be minimum 5")
                @Max(value = 200, message = "Total marks Can't exceed 200") Integer totalMarks,
            @Valid @RequestParam(required = true, value = "stratergy")
                @ApiParam(value = "Stratergy to pick questions from the Store EX : Difficulty, Topic")
                @NotNull String stratergy,
            @Valid @RequestParam(required = true, value = "distributions")
                @ApiParam(value = "Distributions of Questions")
                @NotNull @Size(min = 3,message = "Distribution should be equal to 3") Integer[] distributions,
            @RequestParam(required = false, value = "topicDistributions")
            String[] topicDistributions) {
        try {
            logger.info("Generating Question Paper for TotalMarks = " + totalMarks + " with distributions {}, {} and {} ", distributions[0],distributions[1],+distributions[2]);
            QuestionPaper questionPaper = questionPaperGeneratorService.generateQuestionPapaer(totalMarks, stratergy.toUpperCase(),
                    distributions,topicDistributions);
            return new ResponseEntity<Object>(questionPaper, HttpStatus.OK);
        } catch (QuestionPaperValidationException qe) {
            logger.warn("Could not Generate Question Paper", qe);
	        ApiError apiError = new ApiError(qe.getErrorCode(), qe.getErrorMessage());
            return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
        } catch (QuestionLibraryException e) {
            logger.error("Error occured while generating Question Paper");
	        ApiError apiError = new ApiError(e.getErrorCode(), e.getMessage());
            return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
