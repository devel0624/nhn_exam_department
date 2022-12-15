package com.nhn.exam.department.controller;

import com.nhn.exam.department.domain.entity.DepartmentInfo;
import com.nhn.exam.department.domain.model.projection.DepartmentInfoProjection;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.exception.AcceptHeaderNotExistException;
import com.nhn.exam.department.exception.DoNotRequestAcceptHeaderWithJson;
import com.nhn.exam.department.exception.RequiredParameterNotExistException;
import com.nhn.exam.department.exception.ValidationFailedException;
import com.nhn.exam.department.service.DepartmentInfoService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/department-info")
public class DepartmentInfoController {

  private final DepartmentInfoService departmentInfoService;

  public DepartmentInfoController(DepartmentInfoService departmentInfoService) {
    this.departmentInfoService = departmentInfoService;
  }

  @PostMapping
  public DepartmentInfoProjection registerInfo(@Valid @RequestBody InfoRegisterRequest request,
                                               BindingResult result) {

    if (result.hasErrors()) {
      throw new ValidationFailedException(result);
    }

    return departmentInfoService.getDepartmentInfoByIds(
        new DepartmentInfo.Pk(request.getEmployeeId(), request.getDepartmentId()),
        DepartmentInfoProjection.class
    );
  }

  @GetMapping
  public List<DepartmentInfoProjection> getInfoList(
      @RequestHeader(value = "Accept") Optional<String> accept,
      @RequestParam("departmentIds") List<String> departmentIds) {

    if (accept.isEmpty()) {
      throw new AcceptHeaderNotExistException();
    } else if (!accept.get().equals(MediaType.APPLICATION_JSON.toString())) {
      throw new DoNotRequestAcceptHeaderWithJson();
    } else if (departmentIds.isEmpty()) {
      throw new RequiredParameterNotExistException();
    }

    return departmentInfoService.getDepartmentInfoListByIds(departmentIds,
        DepartmentInfoProjection.class);
  }


}
