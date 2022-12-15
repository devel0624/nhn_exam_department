package com.nhn.exam.department.service.impl;

import com.nhn.exam.department.domain.entity.Department;
import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.entity.Employee;
import com.nhn.exam.department.domain.model.request.DepartmentRegisterRequest;
import com.nhn.exam.department.domain.model.request.EmployeeRegisterRequest;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.domain.repository.DepartmentInfoRepository;
import com.nhn.exam.department.exception.DepartmentInfoNotFoundException;
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


  public DepartmentInfoServiceImpl(DepartmentInfoRepository departmentInfoRepository,
                                   EmployeeService employeeService,
                                   DepartmentService departmentService) {
    this.departmentInfoRepository = departmentInfoRepository;
    this.employeeService = employeeService;
    this.departmentService = departmentService;
  }

  @Transactional(readOnly = false)
  @Override
  public void registerDepartmentInfo(InfoRegisterRequest request) {
    DepartmentInfo departmentInfo = new DepartmentInfo();

    departmentInfo.setPk(new DepartmentInfo.Pk(request.getEmployeeId(), request.getDepartmentId()));
    departmentInfo.setDepartment(departmentService.getDepartmentById(request.getDepartmentId(),
        Department.class));
    departmentInfo.setEmployee(
        employeeService.getEmployeeById(request.getEmployeeId(), Employee.class));

    departmentInfoRepository.saveAndFlush(departmentInfo);
  }

  @Override
  public <T> List<T> getDepartmentInfoListByIds(List<String> ids, Class<T> type) {
    List<T> infoList =
        departmentInfoRepository.findAllByDepartment_IdIn(ids, type);

    if (infoList.isEmpty()) {
      throw new DepartmentInfoNotFoundException();
    }

    return infoList;
  }

  @Override
  public <T> T getDepartmentInfoByIds(DepartmentInfo.Pk pk, Class<T> type) {
    Optional<T> info = departmentInfoRepository.findByPk(pk, type);

    if (info.isEmpty()) {
      throw new DepartmentInfoNotFoundException();
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
