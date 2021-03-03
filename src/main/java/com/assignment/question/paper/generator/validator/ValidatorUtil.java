/*
 *
 */
package com.assignment.question.paper.generator.validator;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.question.paper.generator.config.AppConfig;
import com.assignment.question.paper.generator.constants.ErrorConstant;
import com.assignment.question.paper.generator.exception.QuestionPaperValidationException;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;

/**
 * @author jayavardhan.hegde
 *
 */

@Component
public class ValidatorUtil {

    @Autowired
    private AppConfig config;

    public boolean isDistributionProper(Integer[] distribution) {
        if (distribution[0] + distribution[1] + distribution[2] != 100) {
            throw new QuestionPaperValidationException(ErrorConstant.DISTRIBUTION_NOT_PROPER,
                    config.messageSource().getMessage(ErrorConstant.DISTRIBUTION_NOT_PROPER, null, Locale.ENGLISH));
        }
        return true;
    }

    public boolean isEnoughMarksAvailableToGeneratePaper(int marks) {
	if (marks > QuestionLibrary.TOTAL_MARKS_AVAILABLE) {
            throw new QuestionPaperValidationException(ErrorConstant.NOT_ENOUGH_MARKS, MessageFormat.format(
                    config.messageSource().getMessage(ErrorConstant.NOT_ENOUGH_MARKS, null, Locale.ENGLISH), marks));
        }
        return true;
    }

}
