package com.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
