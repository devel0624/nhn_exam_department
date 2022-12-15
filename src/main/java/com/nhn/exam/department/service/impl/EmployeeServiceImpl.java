package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.repository.EmployeeRepository;
import com.nhn.exam.department.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }
}
