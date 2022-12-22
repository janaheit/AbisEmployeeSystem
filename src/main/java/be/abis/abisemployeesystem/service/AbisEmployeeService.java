package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisEmployeeService implements EmployeeService {
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
}
