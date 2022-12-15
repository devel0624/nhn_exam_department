package com.nhn.exam.department.domain.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class InfoRegisterRequest {
  @Length(max = 20)
  @NotBlank
  String employeeId;

  @Length(max = 10)
  @NotBlank
  String departmentId;
}
