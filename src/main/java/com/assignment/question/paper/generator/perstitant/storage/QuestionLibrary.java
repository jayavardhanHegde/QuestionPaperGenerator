/*
 *
 */
package com.assignment.question.paper.generator.perstitant.storage;

import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * @author jayavardhan.hegde
 *
 */

public class QuestionLibrary {

	public static Integer TOTAL_MARKS_AVAILABLE = 0;

	public static Integer QUESTION_INDEX = 1;

	public static MultiKeyMap multiKeyEasyQuestionsMap = new MultiKeyMap();

	public static MultiKeyMap multiKeyMediumQuestionsMap = new MultiKeyMap();

	public static MultiKeyMap multiKeyDifficultQuestionsMap = new MultiKeyMap();
}
