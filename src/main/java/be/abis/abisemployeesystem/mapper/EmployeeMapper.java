package be.abis.abisemployeesystem.mapper;

import be.abis.abisemployeesystem.dto.EmployeeDTO;
import be.abis.abisemployeesystem.model.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getAbbreviation(), employee.getRoles());
    }
}
