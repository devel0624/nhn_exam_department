package com.nhn.exam.department.controller;

import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
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
  public DepartmentProjection registerDepartment(
      @Valid @RequestBody DepartmentRegisterRequest request, BindingResult result) {

    // TODO 02 발리데이션 에러 처리
    if (result.hasErrors()) {
      throw new RuntimeException("Validation Error");
    }

    return employeeService.registerDepartment(request);
  }

}
