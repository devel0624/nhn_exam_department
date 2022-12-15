package com.nhn.exam.department.domain.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentRegisterRequest {

  @Length(max = 10)
  @NotBlank
  String departmentId;


  @Length(max = 50)
  @NotBlank
  String departmentName;
}
