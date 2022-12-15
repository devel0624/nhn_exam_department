package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;

public interface DepartmentService {
  void registerDepartment(DepartmentRegisterRequest request);

  <T> T getDepartmentById(String id, Class<T> type);
}
