/*
 * 
*/
package com.assignment.question.paper.generator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.assignment.question.paper.generator.perstitant.storage.QuestionLibrary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author jayavardhan.hegde
 *
 */

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "text", "marks" })
public class Question {

    private String text;

    private String subject;

    private String topic;

    private String difficulty;

    private int marks;

    public Question(String text, String subject, String topic, String difficulty, int marks) {
        this.text = text;
        this.subject = subject;
        this.topic = topic;
        this.difficulty = difficulty;
        this.marks = marks;
        QuestionLibrary.TOTAL_MARKS_AVAILABLE = QuestionLibrary.TOTAL_MARKS_AVAILABLE + marks;
    }

    public String getText() {
        return text;
    }

    @JsonProperty("Question")
    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    @JsonIgnore
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    @JsonProperty("Topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @JsonProperty("Difficulty")
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
