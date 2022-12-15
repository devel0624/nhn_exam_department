package com.nhn.exam.department.domain.repository;

import com.nhn.exam.department.domain.entity.Department;
import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
  Optional<DepartmentProjection> findById(String id,
                                          Class<DepartmentProjection> departmentProjectionClass);
}
