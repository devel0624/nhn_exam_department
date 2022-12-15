package com.nhn.exam.department.controller;

import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @PostMapping
  public void registerDepartment(@Valid @RequestBody DepartmentRegisterRequest request) {

  }
}
