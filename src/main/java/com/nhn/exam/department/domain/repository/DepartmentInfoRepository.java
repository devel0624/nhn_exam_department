package com.nhn.exam.department.domain.repository;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo, DepartmentInfo.Pk> {

  <T> List<T> findAllByDepartment_IdIn(List<String> ids, Class<T> type);

  <T> Optional<T> findById(DepartmentInfo.Pk pk, Class<T> type);
}
