package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.dto.LoginDTO;
import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.model.Consultant;
import be.abis.abisemployeesystem.model.Employee;
import be.abis.abisemployeesystem.repository.AbisEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisEmployeeService implements EmployeeService {

    @Autowired
    AbisEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(int id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("This employee does not exist"));
    }

    @Override
    public List<Consultant> getAllConsultants() {
        return employeeRepository.getConsultants();
    }

    @Override
    public List<Employee> getAllNoneConsultants() {
        return employeeRepository.getEmployeesWithoutConsultant();
    }

    @Override
    public List<Employee> getManagers() {
        return employeeRepository.getManagers();
    }

    @Override
    public List<Employee> getAccountants() {
        return null;
    }

    @Override
    public List<Employee> getTeachers() {
        return employeeRepository.getTeachers();
    }

    @Override
    public boolean checkLogin(String abbreviation, String password) {
        Employee employee = employeeRepository.getByAbbreviationAndPassword(abbreviation, password);
        if (employee != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasRole(int employeeId, String role) {
        return (employeeRepository.hasRole(employeeId, role) != null);
    }
}
