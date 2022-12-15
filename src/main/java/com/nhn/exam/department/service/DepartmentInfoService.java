package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.file.request.InitDepartmentInfo;
import java.util.List;

public interface DepartmentInfoService {
  void registerDepartmentInfo(InfoRegisterRequest request);

  List<DepartmentInfoProjection> getDepartmentInfoListByIds(List<String> ids);

  DepartmentInfoProjection getDepartmentInfoByIds(DepartmentInfo.Pk pk);

  void registerDepartmentInfo(InitDepartmentInfo info);
}
