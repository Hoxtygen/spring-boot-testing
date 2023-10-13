package com.codeplanks.springboot.repository;


import com.codeplanks.springboot.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {
  @Autowired
  private EmployeeRepository employeeRepository;

  // JUnit test for save employee operation
  @DisplayName("save employee operation")
  @Test
  public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
    // given - precondition or setup
    Employee employee = Employee.builder()
            .email("shaolin@yahoo.com")
            .firstName("Shaolin")
            .lastName("Dragon")
            .build();

    // when - action or the behaviour we're testing for
    Employee savedEmployee = employeeRepository.save(employee);

    // then - verify the output
    assertThat(savedEmployee).isNotNull();
    assertThat(savedEmployee.getId()).isGreaterThan(0);
  }

  // JUnit test for get all employees
  @DisplayName("get all employees")
  @Test
  public void givenEmployeesList_whenFindAll_thenEmployeesList() {
    // Given - precondition or setup
    Employee employee1 = Employee.builder()
            .email("shaolin@yahoo.com")
            .firstName("Shaolin")
            .lastName("Dragon")
            .build();

    Employee employee2 = Employee.builder()
            .email("dragon@yahoo.com")
            .firstName("Dragon")
            .lastName("Haylin")
            .build();
    employeeRepository.save(employee1);
    employeeRepository.save(employee2);

    // When - action or the behaviour we're testing for
    List<Employee> employeeList = employeeRepository.findAll();

    // Then - verify the output
    assertThat(employeeList).isNotNull();
    assertThat(employeeList.size()).isEqualTo(2);
  }

  // JUnit test for get employee by id
  @DisplayName("get employee by id")
  @Test
  public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
    // Given - precondition or setup
    Employee employee = Employee.builder()
            .email("shaolin@yahoo.com")
            .firstName("Shaolin")
            .lastName("Dragon")
            .build();
    employeeRepository.save(employee);

    // When - action or the behaviour we're testing for
    Employee employeeDB = employeeRepository.findById(employee.getId()).get();

    // Then - verify the output
    assertThat(employeeDB).isNotNull();
  }

  // JUnit test for get employee by email
  @DisplayName("get employee by email")
  @Test
  public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
    // Given - precondition or setup
    Employee employee = Employee.builder()
            .email("shaolin@yahoo.com")
            .firstName("Shaolin")
            .lastName("Dragon")
            .build();
    employeeRepository.save(employee);

    // When - action or the behaviour we're testing for
    Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

    // Then - verify the output
    assertThat(employeeDB).isNotNull();
  }

  // JUnit test for update employee
  @DisplayName("update employee")
  @Test
  public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
    // Given - precondition or setup
    Employee employee = Employee.builder()
            .email("shaolin@yahoo.com")
            .firstName("Shaolin")
            .lastName("Dragon")
            .build();
    employeeRepository.save(employee);

    // When - action or the behaviour we're testing for
    Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
    savedEmployee.setEmail("myelin@example.com");
    savedEmployee.setFirstName("Myelin");
    employeeRepository.save(savedEmployee);

    // Then - verify the output
    assertThat(savedEmployee.getEmail()).isEqualTo("myelin@example.com");
    assertThat(savedEmployee.getFirstName()).isEqualTo("Myelin");
  }

  // JUnit test for delete employee
  @DisplayName("delete employee")
  @Test
  public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
    // Given - precondition or setup
    Employee employee = Employee.builder()
            .email("shaolin@yahoo.com")
            .firstName("Shaolin")
            .lastName("Dragon")
            .build();
    employeeRepository.save(employee);

    // When - action or the behaviour we're testing for
    employeeRepository.deleteById(employee.getId());
    Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());

    // Then - verify the output
    assertThat(optionalEmployee).isEmpty();
  }
}
