package io.somesh.bose.ordermanagement.application.web;

import io.somesh.bose.ordermanagement.application.exception.OrderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({OrderException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<Object> handleTestException(OrderException exception, final WebRequest webRequest){

    return handleExceptionInternal(exception,exception.getLocalizedMessage(),new HttpHeaders(),HttpStatus.BAD_REQUEST,webRequest);
  }
}
