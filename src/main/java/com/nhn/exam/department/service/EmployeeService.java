package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.entity.Employee;
import com.nhn.exam.department.domain.model.projection.EmployeeProjection;
import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;

public interface EmployeeService {
  void registerEmployee(EmployeeRegisterRequest request);

  EmployeeProjection getEmployeeById(String id);

  Employee findById(String id);
}
