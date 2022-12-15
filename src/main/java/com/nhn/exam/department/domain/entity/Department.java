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
@Table(name = "Departments")
public class Department {

  @Id
  @Column(nullable = false, length = 10)
  private String id;

  @NotNull
  @Column(nullable = false, length = 50)
  private String name;
}
