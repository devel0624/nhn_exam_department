package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import com.nhn.exam.department.domain.repository.DepartmentInfoRepository;
import com.nhn.exam.department.service.DepartmentInfoService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentInfoServiceImpl implements DepartmentInfoService {

  private final DepartmentInfoRepository departmentInfoRepository;

  public DepartmentInfoServiceImpl(DepartmentInfoRepository departmentInfoRepository) {
    this.departmentInfoRepository = departmentInfoRepository;
  }

  public List<DepartmentInfoProjection> getDepartmentInfoByIds(List<String> ids) {
    List<DepartmentInfoProjection> infoList =
        departmentInfoRepository.findAllByDepartment_IdIn(ids);

    if (infoList.isEmpty()) {
      // TODO 01 예외처리 작성
      throw new RuntimeException("검색된 부서가 없음");
    }

    return infoList;
  }
}
