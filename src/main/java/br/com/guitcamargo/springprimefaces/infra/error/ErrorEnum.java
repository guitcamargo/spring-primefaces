package br.com.guitcamargo.springprimefaces.infra.error;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {

    PLANETA_NOT_FOUND("Planeta não encontrado para esse ID", HttpStatus.NOT_FOUND);
    private String errorMessage;
    private HttpStatus statusCode;

    ErrorEnum(String errorMessage, HttpStatus statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
