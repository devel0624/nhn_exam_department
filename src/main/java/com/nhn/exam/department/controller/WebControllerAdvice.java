package com.nhn.exam.department.controller;

import com.nhn.exam.department.exception.AcceptHeaderNotExistException;
import com.nhn.exam.department.exception.DoNotRequestAcceptHeaderWithJson;
import com.nhn.exam.department.exception.RequiredParameterNotExistException;
import com.nhn.exam.department.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {


  @ExceptionHandler(AcceptHeaderNotExistException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "ACCEPT 헤더가 존재하지 않습니다.")
  public String acceptHeaderNotExistException(Exception ex) {
    return treatException(ex);
  }

  @ExceptionHandler(DoNotRequestAcceptHeaderWithJson.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "ACCET 헤더가 존재하지만 'application/json' 으로 요청해야합니다.")
  public String doNotRequestAcceptHeaderWithJson(Exception ex) {
    return treatException(ex);
  }


  @ExceptionHandler(ValidationFailedException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "필요한 입력이 존재하지 않거나 잘못된 값이 존재합니다.")
  public String invalidRequestException(Exception ex) {
    return treatException(ex);
  }

  @ExceptionHandler(RequiredParameterNotExistException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "최소 하나 이상의 부서 번호를 파라미터로 할당해주세요")
  public String requiredParameterNotExistException(Exception ex) {
    return treatException(ex);
  }

  @ExceptionHandler(Exception.class)
  public String handleException(Exception ex) {
    return treatException(ex);
  }

  private String treatException(Exception ex) {
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
