package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.entity.Department;
import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;

public interface DepartmentService {
  void registerDepartment(DepartmentRegisterRequest request);

  DepartmentProjection getDepartmentById(String id);

  Department findDepartmentById(String id);
}
