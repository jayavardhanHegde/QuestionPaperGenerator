/*
 *
 */
package com.assignment.question.paper.generator.data.loader;

import static com.assignment.question.paper.generator.constants.ApplicationConstant.TEXT_DIFFICULT;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.assignment.question.paper.generator.model.QuestionLoader;
import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;

/**
 * @author jayavardhan.hegde
 *
 */
public class DifficultQuestionLoader extends AbstractQuestionLoader implements QuestionLoaderStratergy {

	private String metaData;

	private String dataSourcePath;

	public DifficultQuestionLoader() {

	}

	public DifficultQuestionLoader(String metaData, String dataSourcePath) {
		this.metaData = metaData;
		this.dataSourcePath = dataSourcePath;
	}

	@Override
	public QuestionLoader loadQuestion() {
		QuestionLibrary.multiKeyDifficultQuestionsMap.clear();
		Path path = Paths.get(dataSourcePath);
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
			stream.forEach(content -> readContentAndPersistToStore(content.trim(),
			        QuestionLibrary.multiKeyDifficultQuestionsMap, metaData));
			QuestionLibrary.QUESTION_INDEX = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new QuestionLoader(TEXT_DIFFICULT, QuestionLibrary.multiKeyDifficultQuestionsMap.size());
	}

	public String getMetaData() {
	    return metaData;
	}

	public void setMetaData(String metaData) {
	    this.metaData = metaData;
	}

	public String getDataSourcePath() {
	    return dataSourcePath;
	}

	public void setDataSourcePath(String dataSourcePath) {
	    this.dataSourcePath = dataSourcePath;
	}

}
