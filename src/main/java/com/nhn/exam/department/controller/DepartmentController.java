package com.nhn.exam.department.controller;

import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
import com.nhn.exam.department.exception.RequiredParameterNotExistException;
import com.nhn.exam.department.exception.ValidationFailedException;
import com.nhn.exam.department.service.DepartmentService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DepartmentProjection registerDepartment(
      @Valid @RequestBody DepartmentRegisterRequest request, BindingResult result) {

    if (result.hasErrors()) {
      throw new ValidationFailedException(result);
    }

    departmentService.registerDepartment(request);

    return departmentService.getDepartmentById(request.getId(), DepartmentProjection.class);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public DepartmentProjection getDepartment(
      @RequestParam("departmentId") Optional<String> departmentId) {

    if (departmentId.isEmpty()) {
      throw new RequiredParameterNotExistException();
    }

    return departmentService.getDepartmentById(departmentId.get(), DepartmentProjection.class);
  }


}
