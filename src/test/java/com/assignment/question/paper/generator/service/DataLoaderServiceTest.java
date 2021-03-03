package com.assignment.question.paper.generator.service;

import com.assignment.question.paper.generator.QuestionPaperGeneratorApplication;
import com.assignment.question.paper.generator.model.QuestionLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionPaperGeneratorApplication.class})
public class DataLoaderServiceTest {

    @Autowired
    private DataLoaderService dataLoaderService;

    @Test
    public void whenQuestionAreLoadedWithDifficultyLevel(){
        List<QuestionLoader> questionLoaderList = dataLoaderService.loadQuestions("EASY");
        Assert.assertNotNull(questionLoaderList);
    }
}
