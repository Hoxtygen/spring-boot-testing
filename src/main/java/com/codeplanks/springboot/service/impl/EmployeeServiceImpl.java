package com.codeplanks.springboot.service.impl;

import com.codeplanks.springboot.exception.ResourceNotFoundException;
import com.codeplanks.springboot.model.Employee;
import com.codeplanks.springboot.repository.EmployeeRepository;
import com.codeplanks.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee saveEmployee(Employee employee) {
    Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
    if (savedEmployee.isPresent()) {
      throw new ResourceNotFoundException(
              "Employee already exits with the given email " + employee.getEmail());
    }
    return employeeRepository.save(employee);
  }
}
