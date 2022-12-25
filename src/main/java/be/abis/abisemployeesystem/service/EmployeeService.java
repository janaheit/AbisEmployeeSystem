package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.dto.LoginDTO;
import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.model.Consultant;
import be.abis.abisemployeesystem.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee getById(int id) throws EmployeeNotFoundException;
    List<Consultant> getAllConsultants();
    List<Employee> getAllNoneConsultants();
    List<Employee> getManagers();
    List<Employee> getAccountants();
    List<Employee> getTeachers();
    Employee checkLogin(String abbreviation, String password) throws EmployeeNotFoundException;
    boolean hasRole(int employeeId, String role);



}
