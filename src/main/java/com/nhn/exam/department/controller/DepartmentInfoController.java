package com.nhn.exam.department.controller;

import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.service.DepartmentInfoService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department-info")
public class DepartmentInfoController {

  private final DepartmentInfoService departmentInfoService;

  public DepartmentInfoController(DepartmentInfoService departmentInfoService) {
    this.departmentInfoService = departmentInfoService;
  }

  @PostMapping
  public DepartmentInfoProjection registerInfo(@Valid @RequestBody InfoRegisterRequest request,
                                               BindingResult result) {
    //TODO 02 발리데이션 예외 처리
    if (result.hasErrors()) {
      throw new RuntimeException("Info Validation Error");
    }

    return departmentInfoService.
  }
}
