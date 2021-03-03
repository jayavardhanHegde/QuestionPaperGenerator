package com.assignment.question.paper.generator.service;

import com.assignment.question.paper.generator.QuestionPaperGeneratorApplication;
import com.assignment.question.paper.generator.exception.QuestionPaperValidationException;
import com.assignment.question.paper.generator.model.QuestionPaper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionPaperGeneratorApplication.class})
public class QuestionPaperGeneratorServiceTest {

    @Autowired
    private QuestionPaperGeneratorService questionPaperGeneratorService;

    @Autowired
    private DataLoaderService dataLoaderService;

    @Test
    public void whenQuestionPaperGenerationIsSuccessful(){
        dataLoaderService.loadQuestions("ALL");
        Integer[] distributions = new Integer[]{40,20,40};
        QuestionPaper questionPaper = questionPaperGeneratorService.generateQuestionPapaer(5,"DIFFICULTY", distributions, null);
        Assert.assertNotNull(questionPaper);
    }

    @Test(expected = QuestionPaperValidationException.class)
    public void whenQuestionPaperGenerationIsFailed(){
        Integer[] distributions = new Integer[]{50,20,40};
        QuestionPaper questionPaper = questionPaperGeneratorService.generateQuestionPapaer(5,"DIFFICULTY", distributions, null);
        Assert.assertNotNull(questionPaper);
    }
}
