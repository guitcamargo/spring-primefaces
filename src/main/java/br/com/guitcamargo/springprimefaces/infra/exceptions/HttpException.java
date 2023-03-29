package br.com.guitcamargo.springprimefaces.infra.exceptions;

import br.com.guitcamargo.springprimefaces.infra.error.ErrorEnum;
import org.springframework.http.HttpStatus;

public class HttpException  extends RuntimeException {

    private final HttpStatus httpStatus;

    public HttpException(final ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.httpStatus = errorEnum.getStatusCode();
    }

    public HttpException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
