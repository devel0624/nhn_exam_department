package com.nhn.exam.department.domain.repository;

import com.nhn.exam.department.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}