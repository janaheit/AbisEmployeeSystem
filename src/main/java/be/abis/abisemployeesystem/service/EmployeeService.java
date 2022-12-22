package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.dto.LoginDTO;
import be.abis.abisemployeesystem.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();
    Employee getById(int id);
    List<Employee> getAllConsultants();
    List<Employee> getAllNoneConsultants();
    List<Employee> getManagers();
    List<Employee> getAccountants();
    boolean checkLogin(String abbreviation, String password);



}
