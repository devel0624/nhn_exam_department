package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.model.projection.EmployeeProjection;
import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;

public interface EmployeeService {
  EmployeeProjection registerEmployee(EmployeeRegisterRequest request);

  EmployeeProjection getEmployeeById(String id);
}
