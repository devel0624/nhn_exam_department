package com.nhn.exam.department.controller;

import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
import com.nhn.exam.department.service.DepartmentService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  // TODO 조직 추가 컨트롤러

  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @PostMapping
  public DepartmentProjection registerDepartment(
      @Valid @RequestBody DepartmentRegisterRequest request, BindingResult result) {

    // TODO 02 발리데이션 에러 처리
    if (result.hasErrors()) {
      throw new RuntimeException("Department Validation Error");
    }

    return departmentService.registerDepartment(request);
  }


}
