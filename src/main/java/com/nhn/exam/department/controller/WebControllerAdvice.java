package com.nhn.exam.department.controller;

import com.nhn.exam.department.exception.RequiredParameterNotExistException;
import com.nhn.exam.department.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

  @ExceptionHandler(RequiredParameterNotExistException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "최소 하나 이상의 부서 번호를 파라미터로 할당해주세요")
  public String requiredParameterNotExistException(Exception ex) {
    printInfo(ex);
    return printError(ex);
  }

  @ExceptionHandler(ValidationFailedException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid Request")
  public String invalidRequestException(Exception ex) {
    printInfo(ex);
    return printError(ex);
  }

  @ExceptionHandler(Exception.class)
  public String handleException(Exception ex) {
    printInfo(ex);
    return printError(ex);
  }

  private void printInfo(Exception ex) {
    log.info("Exception Class : " + ex.getClass());
  }

  private String printError(Exception ex) {
    log.error("Exception Message : " + ex.getMessage());
    return ex.getMessage();
  }
}
