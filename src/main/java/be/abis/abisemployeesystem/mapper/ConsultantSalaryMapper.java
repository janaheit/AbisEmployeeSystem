package be.abis.abisemployeesystem.mapper;

import be.abis.abisemployeesystem.dto.ConsultantSalaryDTO;
import be.abis.abisemployeesystem.model.Consultant;

public class ConsultantSalaryMapper {

    public static ConsultantSalaryDTO toDTO(Consultant consultant, double salary, long minsWorked){
        ConsultantSalaryDTO dto = new ConsultantSalaryDTO();
        dto.setConsultantId(consultant.getId());
        dto.setName(consultant.getFirstName() + " " + consultant.getLastName());
        dto.setAbbreviation(consultant.getAbbreviation());
        dto.setSalary(salary);
        dto.setMinutesWorked(minsWorked);
        return dto;
    }
}
