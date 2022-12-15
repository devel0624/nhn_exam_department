package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.repository.DepartmentRepository;
import com.nhn.exam.department.domain.repository.EmployeeRepository;
import com.nhn.exam.department.service.DepartmentService;
import com.nhn.exam.department.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }
}
