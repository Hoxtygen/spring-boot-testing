package com.codeplanks.springboot.service;

import com.codeplanks.springboot.model.Employee;
import com.codeplanks.springboot.repository.EmployeeRepository;
import com.codeplanks.springboot.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTests {
  private EmployeeRepository employeeRepository;
  private EmployeeService employeeService;

  @BeforeEach
  public void setup() {
    employeeRepository = Mockito.mock(EmployeeRepository.class);
    employeeService = new EmployeeServiceImpl(employeeRepository);
  }

  // JUnit test for saveEmployee method
  @DisplayName("save employee")
  @Test
  public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
    // Given - precondition or setup
    Employee employee = Employee.builder()
            .id(1L)
            .firstName("Theobroma")
            .lastName("Cacao")
            .email("cacao@example.com")
            .build();
    BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(
            Optional.empty());
    BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

    // When - action or the behaviour we're testing for
    Employee savedEmployee = employeeService.saveEmployee(employee);

    // Then - verify the output
    Assertions.assertThat(savedEmployee).isNotNull();
  }

}
