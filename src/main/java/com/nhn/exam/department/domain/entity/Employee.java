package com.nhn.exam.department.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employees")
public class Employee {

  @Id
  @Column(nullable = false, length = 20)
  private String id;

  @NotNull
  @Column(nullable = false, length = 50)
  private String name;
}
