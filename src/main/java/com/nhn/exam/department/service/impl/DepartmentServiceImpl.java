package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.entity.Department;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
import com.nhn.exam.department.domain.repository.DepartmentRepository;
import com.nhn.exam.department.exception.DepartmentNotFoundException;
import com.nhn.exam.department.service.DepartmentService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepository departmentRepository;

  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  @Transactional(readOnly = false)
  public void registerDepartment(DepartmentRegisterRequest request) {
    Department department = new Department(request.getId(), request.getName());

    departmentRepository.saveAndFlush(department);

  }

  @Override
  public <T> T getDepartmentById(String id, Class<T> type) {
    Optional<T> department = departmentRepository.findById(id, type);

    if (department.isEmpty()) {
      throw new DepartmentNotFoundException();
    }

    return department.get();
  }
}
