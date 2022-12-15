package com.nhn.exam.department.controller;

import com.nhn.exam.department.domain.model.projection.EmployeeProjection;
import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;
import com.nhn.exam.department.exception.ValidationFailedException;
import com.nhn.exam.department.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PostMapping
  public EmployeeProjection registerDepartment(
      @Valid @RequestBody EmployeeRegisterRequest request, BindingResult result) {

    if (result.hasErrors()) {
      throw new ValidationFailedException(result);
    }

    employeeService.registerEmployee(request);

    return employeeService.getEmployeeById(request.getId());
  }

}
