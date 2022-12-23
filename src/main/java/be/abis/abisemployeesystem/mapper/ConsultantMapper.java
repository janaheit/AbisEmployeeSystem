package be.abis.abisemployeesystem.mapper;

import be.abis.abisemployeesystem.dto.ConsultantDTO;
import be.abis.abisemployeesystem.dto.EmployeeDTO;
import be.abis.abisemployeesystem.model.Consultant;
import be.abis.abisemployeesystem.model.Employee;

public class ConsultantMapper {

    public static ConsultantDTO toDTO(Consultant consultant){
        return new ConsultantDTO(consultant.getId(), consultant.getFirstName(), consultant.getLastName(),
                consultant.getAbbreviation(), consultant.getRoles(), consultant.getHourlyRate());
    }
}
