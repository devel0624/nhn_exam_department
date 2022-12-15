package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.entity.Employee;
import com.nhn.exam.department.domain.model.projection.EmployeeProjection;
import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;
import com.nhn.exam.department.domain.repository.EmployeeRepository;
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
  public EmployeeProjection registerEmployee(EmployeeRegisterRequest request) {
    Employee employee = new Employee(request.getId(), request.getName());

    employeeRepository.saveAndFlush(employee);

    return getEmployeeById(request.getId());
  }

  @Override
  public EmployeeProjection getEmployeeById(String id) {
    Optional<EmployeeProjection> employee =
        employeeRepository.findById(id, EmployeeProjection.class);

    //TODO 03 존재하지 않는 사원 예외 처리
    if (employee.isEmpty()) {
      throw new RuntimeException("부서 존재하지 않는 부서 코드");
    }

    return employee.get();
  }

}
