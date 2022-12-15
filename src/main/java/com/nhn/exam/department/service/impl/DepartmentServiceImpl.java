package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.entity.Department;
import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
import com.nhn.exam.department.domain.repository.DepartmentRepository;
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
  public DepartmentProjection registerDepartment(DepartmentRegisterRequest request) {
    Department department = new Department(request.getId(), request.getName());

    departmentRepository.saveAndFlush(department);

    return getDepartmentById(request.getId());
  }

  @Override
  public DepartmentProjection getDepartmentById(String id) {
    Optional<DepartmentProjection> department =
        departmentRepository.findById(id, DepartmentProjection.class);

    //TODO 03 존재하지 않는 부서 예외 처리
    if (department.isEmpty()) {
      throw new RuntimeException("부서 존재하지 않는 부서 코드");
    }

    return department.get();
  }
}
