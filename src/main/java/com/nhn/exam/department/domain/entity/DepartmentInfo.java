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
@Table(name = "Department_Info")
public class DepartmentInfo {
  @EmbeddedId
  private Pk pk;
  
  @MapsId("employeeId")
  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @MapsId("departmentId")
  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  @Embeddable
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Setter
  public static class Pk implements Serializable {

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "department_id")
    private String departmentId;
  }
}
