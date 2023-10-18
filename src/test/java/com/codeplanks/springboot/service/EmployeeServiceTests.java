package com.codeplanks.springboot.service;

import com.codeplanks.springboot.exception.ResourceNotFoundException;
import com.codeplanks.springboot.model.Employee;
import com.codeplanks.springboot.repository.EmployeeRepository;
import com.codeplanks.springboot.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  private Employee employee;

  @BeforeEach
  public void setup() {
    employee = Employee.builder()
            .id(1L)
            .firstName("Theobroma")
            .lastName("Cacao")
            .email("cacao@example.com")
            .build();
  }

  // JUnit test for saveEmployee method
  @DisplayName("save employee")
  @Test
  public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
    // Given - precondition or setup
    given(employeeRepository.findByEmail(employee.getEmail())).willReturn(
            Optional.empty());
    given(employeeRepository.save(employee)).willReturn(employee);

    // When - action or the behaviour we're testing for
    Employee savedEmployee = employeeService.saveEmployee(employee);

    // Then - verify the output
    Assertions.assertThat(savedEmployee).isNotNull();
  }

  // JUnit test for saveEmployee that throws exception
  @DisplayName("save employee with exception")
  @Test
  public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {
    // Given - precondition or setup
    given(employeeRepository.findByEmail(employee.getEmail())).willReturn(
            Optional.of(employee));

    // When - action or the behaviour we're testing for
    org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      employeeService.saveEmployee(employee);
    });

    // Then - verify the output
    verify(employeeRepository, never()).save(any(Employee.class));
  }

}
