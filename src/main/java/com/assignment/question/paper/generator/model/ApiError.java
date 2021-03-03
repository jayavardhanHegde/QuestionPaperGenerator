/*

 */
package com.assignment.question.paper.generator.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author jayavardhan.hegde
 *
 */

@JsonSerialize
public class ApiError {

    private String errorMessage;

    private String errorCode;

    public ApiError(String errorMessage, String errorCode) {
	this.errorMessage = errorMessage;
	this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
