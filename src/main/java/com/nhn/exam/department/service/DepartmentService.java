package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;

public interface DepartmentService {
  DepartmentProjection registerDepartment(DepartmentRegisterRequest request);
}
