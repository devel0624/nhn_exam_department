package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;

public interface EmployeeService {
  void registerEmployee(EmployeeRegisterRequest request);

  <T> T getEmployeeById(String id, Class<T> type);
}
