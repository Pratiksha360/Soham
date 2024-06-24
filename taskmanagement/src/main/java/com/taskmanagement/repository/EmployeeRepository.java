package com.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskmanagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
