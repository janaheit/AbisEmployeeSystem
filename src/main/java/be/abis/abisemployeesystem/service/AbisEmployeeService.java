package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.dto.LoginDTO;
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
        return null;
    }

    @Override
    public Employee getById(int id) {
        return null;
    }

    @Override
    public List<Employee> getAllConsultants() {
        return null;
    }

    @Override
    public List<Employee> getAllNoneConsultants() {
        return null;
    }

    @Override
    public List<Employee> getManagers() {
        return null;
    }

    @Override
    public List<Employee> getAccountants() {
        return null;
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
}
