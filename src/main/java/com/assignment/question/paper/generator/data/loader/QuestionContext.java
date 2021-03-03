/*
 *
 */
package com.assignment.question.paper.generator.data.loader;

import com.assignment.question.paper.generator.model.QuestionLoader;

/**
 * @author jayavardhan.hegde
 *
 */
public class QuestionContext {

    private QuestionLoaderStratergy questionLoaderStratergy;

    public QuestionContext(QuestionLoaderStratergy strategy) {
        this.questionLoaderStratergy = strategy;
    }

    public QuestionLoader loadQuestion() {
        return questionLoaderStratergy.loadQuestion();
    }
}
