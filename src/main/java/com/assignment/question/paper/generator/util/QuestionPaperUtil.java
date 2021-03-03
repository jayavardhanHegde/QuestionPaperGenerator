/*
 *
 */
package com.assignment.question.paper.generator.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author jayavardhan.hegde
 *
 */

@Component
public class QuestionPaperUtil {

	public Map<String, Double> calculateDistributionOfQuestions(Integer totalMarks, Integer[] distributions) {
	    Map<String, Double> distributionMap = new HashMap<String, Double>();
	    distributionMap.put("EASY", (double) (5 * (Math.round(getMarks(totalMarks, distributions[0]) / 5))));
	    // added ternary to eliminate null pointer
	    distributionMap.put("DIFFICULT",
		    ((totalMarks - distributionMap.get("EASY")) / 10) > 1
			    ? (double) (10 * (Math.round(getMarks(totalMarks, distributions[2]) / 10)))
			    : 0.0);
	    distributionMap.put("MEDIUM",
		    ((totalMarks - (distributionMap.get("EASY") + distributionMap.get("DIFFICULT"))) / 5) >= 1
			    ? (double) (5 * (Math.round((double) totalMarks / 5)))
				    - (distributionMap.get("EASY") + distributionMap.get("DIFFICULT"))
			    : 0.0);

	    return distributionMap;
	}

	public Map<String,Double> calculateDistributionOfQuestions(Integer totalMarks,String[] topicDistributions){
		Map<String, Double> distributionMap = new HashMap<String, Double>();
		//To-DO : Implement the logic to calculate the distributions based on Topic
		return distributionMap;
	}

	/**
	 * @param totalMarks
	 * @param distributions
	 * @return
	 */
	private double getMarks(double totalMarks, double distribution) {
		return totalMarks * (distribution / 100);
	}
}
