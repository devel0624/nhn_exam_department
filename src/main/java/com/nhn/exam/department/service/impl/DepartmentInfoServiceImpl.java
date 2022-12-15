package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.domain.repository.DepartmentInfoRepository;
import com.nhn.exam.department.service.DepartmentInfoService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentInfoServiceImpl implements DepartmentInfoService {

  private final DepartmentInfoRepository departmentInfoRepository;
  
  public DepartmentInfoServiceImpl(DepartmentInfoRepository departmentInfoRepository) {
    this.departmentInfoRepository = departmentInfoRepository;
  }

  @Override
  @Transactional(readOnly = false)
  public DepartmentInfoProjection registerDepartmentInfo(InfoRegisterRequest request) {
    DepartmentInfo departmentInfo = new DepartmentInfo();
    departmentInfo.setPk(new DepartmentInfo.Pk(request.getEmployeeId(), request.getDepartmentId()));

    departmentInfoRepository.saveAndFlush(departmentInfo);

    return getDepartmentInfoByIds(departmentInfo.getPk());
  }

  @Override
  public List<DepartmentInfoProjection> getDepartmentInfoListByIds(List<String> ids) {
    List<DepartmentInfoProjection> infoList =
        departmentInfoRepository.findAllByDepartment_IdIn(ids, DepartmentInfoProjection.class);

    if (infoList.isEmpty()) {
      // TODO 01 예외처리 작성
      throw new RuntimeException("검색된 조직 정보가 없음");
    }

    return infoList;
  }

  @Override
  public DepartmentInfoProjection getDepartmentInfoByIds(DepartmentInfo.Pk pk) {
    Optional<DepartmentInfoProjection> info =
        departmentInfoRepository.findById(pk, DepartmentInfoProjection.class);

    if (info.isEmpty()) {
      // TODO 01 예외처리 작성
      throw new RuntimeException("검색된 조직 정보가 없음");
    }

    return info.get();
  }
}
