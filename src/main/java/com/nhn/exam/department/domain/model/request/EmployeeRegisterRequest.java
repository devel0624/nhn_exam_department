package com.nhn.exam.department.domain.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRegisterRequest {

  @Length(max = 20)
  @NotBlank
  String id;


  @Length(max = 30)
  @NotBlank
  String name;
}
