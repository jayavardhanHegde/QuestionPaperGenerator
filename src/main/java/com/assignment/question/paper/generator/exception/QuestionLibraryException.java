/*
 */
package com.assignment.question.paper.generator.exception;

/**
 * @author jayavardhan.hegde
 *
 */
public class QuestionLibraryException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;

    public QuestionLibraryException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
