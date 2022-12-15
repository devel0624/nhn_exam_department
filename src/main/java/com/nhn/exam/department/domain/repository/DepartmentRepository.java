package com.nhn.exam.department.domain.repository;

import com.nhn.exam.department.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,String > {
}
