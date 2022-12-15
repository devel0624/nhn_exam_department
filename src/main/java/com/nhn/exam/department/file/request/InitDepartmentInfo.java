package com.nhn.exam.department.file.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitDepartmentInfo {

  String employeeId;
  String employeeName;
  String departmentName;
  String departmentId;

  @Override
  public String toString() {
    return "{\n" +
        "employeeId : " + employeeId + "\n" +
        "employeeName : " + employeeName + "\n" +
        "departmentName : " + departmentName + "\n" +
        "departmentId : " + departmentId + "\n" +
        "}";
  }
}
