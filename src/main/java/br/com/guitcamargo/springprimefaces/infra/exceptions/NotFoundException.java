package br.com.guitcamargo.springprimefaces.infra.exceptions;

import br.com.guitcamargo.springprimefaces.infra.error.ErrorEnum;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

    private static final long serialVersionUID = 3068313744413327300L;


    public NotFoundException(final ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public NotFoundException(final String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
