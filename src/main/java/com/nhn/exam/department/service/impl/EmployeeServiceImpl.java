package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.entity.Employee;
import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;
import com.nhn.exam.department.domain.repository.EmployeeRepository;
import com.nhn.exam.department.exception.EmployeeNotFoundException;
import com.nhn.exam.department.service.EmployeeService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional(readOnly = false)
  public void registerEmployee(EmployeeRegisterRequest request) {
    Employee employee = new Employee(request.getId(), request.getName());

    employeeRepository.saveAndFlush(employee);

  }

  @Override
  public <T> T getEmployeeById(String id, Class<T> type) {
    Optional<T> employee = employeeRepository.findById(id, type);

    if (employee.isEmpty()) {
      throw new EmployeeNotFoundException();
    }

    return employee.get();
  }

}
