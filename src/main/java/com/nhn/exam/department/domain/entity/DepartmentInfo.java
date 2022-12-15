package com.nhn.exam.department.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Department_info")
public class DepartmentInfo {
  @EmbeddedId
  private Pk pk;

  @MapsId("employeeId")
  @ManyToOne
  @JoinColumn(name = "employee_id", columnDefinition = "VARCHAR(20)", nullable = false)
  private Employee employee;

  @MapsId("departmentId")
  @ManyToOne
  @JoinColumn(name = "department_id", columnDefinition = "VARCHAR(10)", nullable = false)
  private Department department;

  @Embeddable
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Setter
  public static class Pk implements Serializable {

    @NotNull
    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @NotNull
    @Column(name = "department_id", nullable = false)
    private String departmentId;
  }
}
