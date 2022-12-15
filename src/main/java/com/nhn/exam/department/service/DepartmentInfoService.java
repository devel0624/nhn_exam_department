package com.nhn.exam.department.service;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.file.request.InitDepartmentInfo;
import java.util.List;

public interface DepartmentInfoService {
  void registerDepartmentInfo(InfoRegisterRequest request);

  <T> List<T> getDepartmentInfoListByIds(List<String> ids, Class<T> type);

  <T> T getDepartmentInfoByIds(DepartmentInfo.Pk pk, Class<T> type);

  void registerDepartmentInfo(InitDepartmentInfo info);
}
