package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface DepartmentInfoService {
  DepartmentInfoProjection registerDepartmentInfo(InfoRegisterRequest request);

  List<DepartmentInfoProjection> getDepartmentInfoListByIds(List<String> ids);

  DepartmentInfoProjection getDepartmentInfoByIds(DepartmentInfo.Pk pk);
}
