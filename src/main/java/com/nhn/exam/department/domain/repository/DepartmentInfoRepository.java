package com.nhn.exam.department.domain.repository;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo, DepartmentInfo.Pk> {

  <T> List<T> findAllByDepartment_IdIn(List<String> ids, Class<T> type);

  <T> Optional<T> findByPk(DepartmentInfo.Pk pk, Class<T> type);

  @Deprecated
  @Modifying
  @Query(value = "insert Department_Info(department_id, employee_id) values (?1,?2)", nativeQuery = true)
  void insertInfo(String departmentId, String employeeId);

}
