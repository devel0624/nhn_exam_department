package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.domain.repository.DepartmentInfoRepository;
import com.nhn.exam.department.domain.repository.DepartmentRepository;
import com.nhn.exam.department.file.request.InitDepartmentInfo;
import com.nhn.exam.department.service.DepartmentInfoService;
import com.nhn.exam.department.service.DepartmentService;
import com.nhn.exam.department.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentInfoServiceImpl implements DepartmentInfoService {

  private final DepartmentInfoRepository departmentInfoRepository;

  private final EmployeeService employeeService;
  private final DepartmentService departmentService;
  private final DepartmentRepository departmentRepository;

  public DepartmentInfoServiceImpl(DepartmentInfoRepository departmentInfoRepository,
                                   EmployeeService employeeService,
                                   DepartmentService departmentService,
                                   DepartmentRepository departmentRepository) {
    this.departmentInfoRepository = departmentInfoRepository;
    this.employeeService = employeeService;
    this.departmentService = departmentService;
    this.departmentRepository = departmentRepository;
  }

  @Override
  @Transactional(readOnly = false)
  public void registerDepartmentInfo(InfoRegisterRequest request) {
    DepartmentInfo departmentInfo = new DepartmentInfo();

    departmentInfo.setPk(new DepartmentInfo.Pk(request.getEmployeeId(), request.getDepartmentId()));
    departmentInfo.setDepartment(departmentService.findDepartmentById(request.getDepartmentId()));
    departmentInfo.setEmployee(employeeService.findById(request.getEmployeeId()));

    departmentInfoRepository.saveAndFlush(departmentInfo);
    
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
        departmentInfoRepository.findByPk(pk, DepartmentInfoProjection.class);

    if (info.isEmpty()) {
      // TODO 01 예외처리 작성
      throw new RuntimeException("검색된 조직 정보가 없음");
    }

    return info.get();
  }

  @Transactional(readOnly = false)
  @Override
  public void registerDepartmentInfo(InitDepartmentInfo info) {
    EmployeeRegisterRequest employeeRegisterRequest = new EmployeeRegisterRequest();
    employeeRegisterRequest.setId(info.getEmployeeId());
    employeeRegisterRequest.setName(info.getEmployeeName());

    employeeService.registerEmployee(employeeRegisterRequest);

    DepartmentRegisterRequest departmentRegisterRequest = new DepartmentRegisterRequest();
    departmentRegisterRequest.setId(info.getDepartmentId());
    departmentRegisterRequest.setName(info.getDepartmentName());

    departmentService.registerDepartment(departmentRegisterRequest);

    InfoRegisterRequest infoRegisterRequest = new InfoRegisterRequest();
    infoRegisterRequest.setDepartmentId(info.getDepartmentId());
    infoRegisterRequest.setEmployeeId(info.getEmployeeId());

    registerDepartmentInfo(infoRegisterRequest);
  }
}
