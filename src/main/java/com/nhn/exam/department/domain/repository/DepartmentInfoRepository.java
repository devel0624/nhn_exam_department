package com.nhn.exam.department.domain.repository;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.DepartmentInfoDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo, DepartmentInfo.Pk> {

  List<DepartmentInfoDTO> findAllByDepartment_IdIn(List<String> ids);
}
