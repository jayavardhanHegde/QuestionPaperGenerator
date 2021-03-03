/*
 *
 */
package com.assignment.question.paper.generator.data.loader;

import org.apache.commons.collections4.map.MultiKeyMap;

import com.assignment.question.paper.generator.model.Question;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;

/**
 * @author jayavardhan.hegde
 *
 */
public class AbstractQuestionLoader {

    public void readContentAndPersistToStore(String content, MultiKeyMap<Comparable, Question> map, String metaData) {
		String[] splitContent = content.split(",");
		Question question = new Question(splitContent[0], splitContent[1], splitContent[2], splitContent[3],
		        Integer.valueOf(splitContent[4]));
		map.put(QuestionLibrary.QUESTION_INDEX, question.getDifficulty(), question);
		if (metaData.contains("TOPIC")) {
			map.put(QuestionLibrary.QUESTION_INDEX, question.getDifficulty(), question.getTopic(), question);
		}
		QuestionLibrary.QUESTION_INDEX++;
	}
}
