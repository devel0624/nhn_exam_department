package com.nhn.exam.department.domain.model.projection;

public interface DepartmentInfoProjection {
  DepartmentProjection getDepartment();

  EmployeeProjection getEmployee();
}
