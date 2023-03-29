package br.com.guitcamargo.springprimefaces.infra.error.handling;

import br.com.guitcamargo.springprimefaces.infra.error.resource.ErrorResource;
import br.com.guitcamargo.springprimefaces.infra.exceptions.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResource> notFoundException(final HttpException exception,
                                                           final HttpServletRequest request) {
        final ErrorResource error = new ErrorResource(request.getRequestURI(), exception.getMessage(),
                exception.getHttpStatus().value());
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResource> methodNotSupported(final HttpRequestMethodNotSupportedException exception,
                                                            final HttpServletRequest request) {
        final ErrorResource error = new ErrorResource(request.getRequestURI(), exception.getMessage(), 405);
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResource> messageNotReadable(final HttpMessageNotReadableException exception,
                                                            final HttpServletRequest request) {
        final ErrorResource error = new ErrorResource(request.getRequestURI(), exception.getMessage(), 405);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResource> exception(final Throwable throwable, final HttpServletRequest request) {
        String message = throwable.getCause() != null ? throwable.getCause().getMessage() : throwable.getMessage();
        final ErrorResource errorResource = new ErrorResource(request.getRequestURI(), message,
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResource, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResource> illegalArgumentException(final Exception exception,
                                                                  final HttpServletRequest request) {
        final ErrorResource exceptionResource = new ErrorResource(request.getRequestURI(), exception.getMessage(),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResource, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResource> constraintViolationException(final ConstraintViolationException exception,
                                                                      final HttpServletRequest request) {

        Iterator<ConstraintViolation<?>> iterator = exception.getConstraintViolations().iterator();
        List<String> errors = new ArrayList<>();

        while (iterator.hasNext()) {
            ConstraintViolation<?> constraintViolation = iterator.next();

            errors.add(fieldConstraintName(constraintViolation) + ": " + constraintViolation.getMessage());
        }

        final ErrorResource exceptionResource = new ErrorResource(request.getRequestURI());

        errors.stream().sorted().forEach(error -> {
            exceptionResource.getErrors().addErrorMessage(error, HttpStatus.BAD_REQUEST.value());
        });

        return new ResponseEntity<>(exceptionResource, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> methodArgumentNotValid(final MethodArgumentNotValidException exception,
                                                                final HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage()));

        exception.getBindingResult().getGlobalErrors().forEach(
                globalError -> errors.add(globalError.getObjectName() + ": " + globalError.getDefaultMessage()));

        final ErrorResource exceptionResource = new ErrorResource(request.getRequestURI());

        errors.stream()
                .forEach(error -> exceptionResource.getErrors().addErrorMessage(error, HttpStatus.BAD_REQUEST.value()));

        return new ResponseEntity<>(exceptionResource, HttpStatus.BAD_REQUEST);
    }

    private String fieldConstraintName(ConstraintViolation<?> constraintViolation) {
        String constraintName = constraintViolation.getPropertyPath().toString();

        if (constraintName.indexOf(".") > 0 && constraintName.indexOf("[") > 0) {
            constraintName = constraintName.substring(constraintName.indexOf(".") + 1);
        }

        return constraintName;
    }
}
