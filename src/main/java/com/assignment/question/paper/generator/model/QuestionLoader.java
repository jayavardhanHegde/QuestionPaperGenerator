/*
 
 *
 */
package com.assignment.question.paper.generator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jayavardhan.hegde
 *
 */
public class QuestionLoader {

    private String questionType;

    private int noOfLoadedQuestions;

    public QuestionLoader(String questionType, int noOfLoadedQuestions) {
        this.questionType = questionType;
        this.noOfLoadedQuestions = noOfLoadedQuestions;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getNoOfLoadedQuestions() {
        return noOfLoadedQuestions;
    }

    public void setNoOfLoadedQuestions(int noOfLoadedQuestions) {
        this.noOfLoadedQuestions = noOfLoadedQuestions;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
