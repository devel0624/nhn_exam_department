package com.nhn.exam.department.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhn.exam.department.domain.entity.Department;
import com.nhn.exam.department.domain.model.projection.DepartmentProjection;
import com.nhn.exam.department.domain.model.projection.EmployeeProjection;
import com.nhn.exam.department.domain.model.request.InfoRegisterRequest;
import com.nhn.exam.department.domain.repository.DepartmentRepository;
import com.nhn.exam.department.domain.repository.EmployeeRepository;
import com.nhn.exam.department.exception.AcceptHeaderNotExistException;
import com.nhn.exam.department.exception.NotRequestedAcceptHeaderWithJson;
import com.nhn.exam.department.exception.RequiredParameterNotExistException;
import com.nhn.exam.department.service.DepartmentInfoService;
import com.nhn.exam.department.service.DepartmentService;
import com.nhn.exam.department.service.EmployeeService;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class DepartmentInfoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  DepartmentInfoService departmentInfoService;

  @SpyBean
  EmployeeRepository employeeRepository;

  @SpyBean
  DepartmentRepository departmentRepository;

  @Test
  @DisplayName("Accept Header 가 주어지지 않음")
  void getDepartmentInfoWithout_AcceptHeader() throws Exception {
    mockMvc.perform(get("/department-info"))
        .andExpect(
            (result) -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getClass()
                .isAssignableFrom(AcceptHeaderNotExistException.class)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Accept Header 가 주어졌으나 APPLICATION_JSON이 아님 ")
  void getDepartmentInfo_AcceptHeader() throws Exception {
    mockMvc.perform(get("/department-info")
            .accept(MediaType.APPLICATION_XML))
        .andExpect(
            (result) -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getClass()
                .isAssignableFrom(NotRequestedAcceptHeaderWithJson.class)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("departmentId가 주어지지 않음 ")
  void getDepartmentInfoWithout_DepartmentId() throws Exception {
    mockMvc.perform(get("/department-info")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(
            (result) -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getClass()
                .isAssignableFrom(RequiredParameterNotExistException.class)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("departmentId가 주어졌으나 비어있음")
  void getDepartmentInfoWith_DepartmentId_ButEmpty() throws Exception {
    mockMvc.perform(get("/department-info")
            .accept(MediaType.APPLICATION_JSON)
            .param("departmentId", ""))
        .andExpect(
            (result) -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getClass()
                .isAssignableFrom(RequiredParameterNotExistException.class)))
        .andExpect(status().isBadRequest());
  }

  //  @Test
  void postDepartmentInfo() throws Exception {
    InfoRegisterRequest request = new InfoRegisterRequest();
    request.setDepartmentId("L1001");
    request.setEmployeeId("20174299");

    DepartmentProjection departmentProjection = new DepartmentProjection() {
      @Override
      public String getId() {
        return "L1001";
      }

      @Override
      public String getName() {
        return "개발 2과";
      }
    };

    EmployeeProjection employeeProjection = new EmployeeProjection() {
      @Override
      public String getId() {
        return "20174299";
      }

      @Override
      public String getName() {
        return "김개발";
      }
    };

    given(
        departmentRepository.findById(request.getDepartmentId(), DepartmentProjection.class))
        .willReturn(Optional.of(departmentProjection));

    given(employeeRepository.findById(request.getEmployeeId(), EmployeeProjection.class))
        .willReturn(Optional.of(employeeProjection));


    ObjectMapper mapper = new ObjectMapper();

    mockMvc.perform(post("/department-info")
            .content(mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.employee.id", equalTo("20174299")));
  }
}